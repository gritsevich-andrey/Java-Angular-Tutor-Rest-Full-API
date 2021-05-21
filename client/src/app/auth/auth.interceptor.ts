import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {throwError} from 'rxjs';
import {Router} from '@angular/router';
import {catchError} from 'rxjs/operators';
import {TokenStorageService} from '../services/token-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: TokenStorageService,
              private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.auth.isAuthenticated()) {
      req = req.clone({
        setParams: {
          // @ts-ignore
          auth: this.auth.token
        }
      });
    }
    return next.handle(req)
      .pipe(
        catchError(error => {
          if (error.status === 401) {
            this.auth.logOut();
            this.router.navigate(['/login']);
          }
          return throwError(error);
        })
      );

  }
}

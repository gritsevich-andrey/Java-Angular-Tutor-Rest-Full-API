import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  public login(user: any): Observable<any> {
    return this.http.post(environment.AUTH_API, {
      email: user.email,
      password: user.password
    });
  }

  public registerUser(user: any): Observable<any> {
    return this.http.post(environment.REG_API, {
      email: user.email,
      password: user.password,
      roles: user.roles = ['PUPIL']
    });
  }
  public registerUserFromAdmin(user: any): Observable<any> {
    return this.http.post(environment.REG_API, {
      email: user.email,
      password: user.password,
      roles: [user.role]
    });
  }
}

import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../services/token-storage.service';
import {NotificationService} from '../../services/notification.service';
import {AuthService} from '../../services/auth.service';
import {of} from 'rxjs';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form?: FormGroup | undefined;
  email: string | undefined;

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private notificationService: NotificationService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      email: new FormControl(null, [
        Validators.required,
        Validators.email
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(6)
      ])
    });
  }

  submit(): void {
    // @ts-ignore
    if (this.form.invalid) {
      return;
    }
    // @ts-ignore
    // @ts-ignore
    this.authService.login({
      // @ts-ignore
      email: this.form.value.email,
      // @ts-ignore
      password: this.form.value.password
    }).subscribe(data => {
      const myRole = 'ADMIN';
      const roles = data.roles as Array<string>;
      if (!roles || roles.indexOf(myRole) !== -1) {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);
        localStorage.setItem('role', JSON.stringify({role: data.roles}));
        this.notificationService.showSnackBar('Вход успешный');
        this.router.navigate(['/admin', 'users']);
      } else {
        this.notificationService.showSnackBar('Вы не администратор');
        this.router.navigate(['/login']);
      }
    }, error => {
      console.log(error);
      return of(false);
    });
  }
}

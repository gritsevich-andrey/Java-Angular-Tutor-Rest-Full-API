import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {NotificationService} from '../../services/notification.service';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../services/token-storage.service';

@Component({
  selector: 'app-edit-page',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  public form: FormGroup | any;

  constructor(private authService: AuthService,
              private notificationService: NotificationService,
              private router: Router,
              public ts: TokenStorageService
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
      ]),
      role: new FormControl('PUPIL')
    });
  }

  submit(): void {
    if (this.form.invalid) {
      return;
    }
    this.authService.registerUserFromAdmin({
      email: this.form.value.email,
      password: this.form.value.password,
      role: this.form.value.role
    }).subscribe(data => {
      console.log(data);
      this.notificationService.showSnackBar('Пользователь создан');
      this.router.navigate(['/admin', 'users']);
      // window.location.reload();

    }, error => {
      this.notificationService.showSnackBar(error.message = 'Ошибка создания пользователя');
    });
  }
}

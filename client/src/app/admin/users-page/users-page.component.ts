import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormControl, FormGroup} from '@angular/forms';
import {Users} from '../../shared/interfaces';
import {UserService} from '../../services/user.service';
import {environment} from '../../../environments/environment';
import {TokenStorageService} from '../../services/token-storage.service';
import {NotificationService} from '../../services/notification.service';
import {MatDialog} from '@angular/material/dialog';
import {CreateUserComponent} from '../create-user/create-user.component';

@Component({
  selector: 'app-users-page',
  templateUrl: './users-page.component.html',
  styleUrls: ['./users-page.component.css']
})
export class UsersPageComponent implements OnInit {
  users: Users[] = [];
  form?: FormGroup | undefined;
  submitted = false;
  role: string[] = [];
  totalLength: number | undefined;
  page: number = 1;
  // https://www.npmjs.com/package/ngx-pagination
  userEmail: string| any = '';

  constructor(private http: HttpClient,
              private userService: UserService,
              public ts: TokenStorageService,
              private notificationService: NotificationService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      role: new FormControl('PUPIL')
    });
    this.http.get<Users[]>(environment.USER_API).subscribe(users => {
      this.users = users;
      this.totalLength = users.length;
    });
  }

  removeUser(userId: string): void {
    this.http.delete<void>(environment.USER_API + '/' + userId)
      .subscribe(() => {
        this.users = this.users.filter(t => t.userId !== userId);
      });
    this.notificationService.showSnackBar('Пользователь удален');
  }

  updateRole(userId: string): any {
    // @ts-ignore
    if (this.form.invalid) {
      return;
    }
    this.submitted = true;
    // @ts-ignore
    this.userService.updateUser({
      userId,
      // @ts-ignore
      roles: this.form.value.role
    });
    this.notificationService.showSnackBar('Роль обновлена');
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CreateUserComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}

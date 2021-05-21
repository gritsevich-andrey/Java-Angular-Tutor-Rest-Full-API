import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../../../services/token-storage.service';
import {Router} from '@angular/router';
import {NotificationService} from '../../../../services/notification.service';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent implements OnInit {
  links = [
    {url: '/admin/users', name: 'Пользователи'},
    {url: '/admin/programs', name: 'Программы'},
    {url: '/admin/lessons', name: 'Уроки'}
  ];

  constructor(public tokenStorageService: TokenStorageService, private router: Router,
              private notificationService: NotificationService) {
  }

  logout(): void {
    this.tokenStorageService.logOut();
  }

  ngOnInit(): void {
    if (!this.tokenStorageService.getRole()) {
      this.notificationService.showSnackBar('Вы не являетесь администратором');
      this.tokenStorageService.logOut();
      this.router.navigate(['login']);
    }
    this.router.navigate(['admin', 'login']);
  }
}

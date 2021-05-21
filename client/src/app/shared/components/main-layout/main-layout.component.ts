import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.css']
})
export class MainLayoutComponent implements OnInit {

  constructor(
    private router: Router,
    private tokenStorage: TokenStorageService) {
      if (this.tokenStorage.getUser()) {
        this.isVisible = false;
      }
     }

  isVisible: boolean = true;

  ngOnInit(): void {
  }

  logout(): void {
    this.tokenStorage.logOut();
  }

}

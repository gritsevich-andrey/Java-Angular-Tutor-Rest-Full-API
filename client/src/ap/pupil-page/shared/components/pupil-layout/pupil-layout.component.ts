import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../../../services/token-storage.service';
import {of} from 'rxjs';
import {Router} from '@angular/router';
import firebase from 'firebase/app';
import 'firebase/auth';
const config = {
  apiKey: 'AIzaSyB32Jd1DlgDWYQrdIYIQ1cBb9G6lf95BRY',
  databaseURL: 'https://angular-chat-28920-default-rtdb.firebaseio.com'
};
@Component({
  selector: 'app-pupil-layout',
  templateUrl: './pupil-layout.component.html',
  styleUrls: ['./pupil-layout.component.css']
})
export class PupilLayoutComponent implements OnInit {

  constructor(public tokenStorageService: TokenStorageService,
              private router: Router) {
    if (!firebase.apps.length) {
      firebase.initializeApp(config);
    }else {
      firebase.app();
    }
  }

  ngOnInit(): void {
    const potentialToken = window.sessionStorage.getItem('auth-token');
    if (potentialToken !== null) {
      this.tokenStorageService.saveToken(potentialToken);
    }
    const currentUser = this.tokenStorageService.getUser();
    if (currentUser) {
      // @ts-ignore
      return of(true);

    }

    // @ts-ignore
    return of(false);
  }

  logout(): void {
    this.tokenStorageService.logOut();
  }
}

import {Component, OnInit} from '@angular/core';
import firebase from 'firebase';

// const config = {
//   apiKey: 'AIzaSyB32Jd1DlgDWYQrdIYIQ1cBb9G6lf95BRY',
//   authDomain: 'angular-chat-28920.firebaseapp.com',
//   databaseURL: 'https://angular-chat-28920-default-rtdb.firebaseio.com',
//   projectId: 'angular-chat-28920'
// };
@Component({
  selector: 'app-private-chat',
  templateUrl: './private-chat.component.html',
  styleUrls: ['./private-chat.component.css']
})
export class PrivateChatComponent implements OnInit {
  title = 'private-websocket';

  constructor() {
    // firebase.initializeApp(config);
  }

  ngOnInit(): void {
  }
}

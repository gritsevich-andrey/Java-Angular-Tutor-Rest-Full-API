import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import firebase from 'firebase';
import {DatePipe} from '@angular/common';

export const snapshotToArray = (snapshot: any) => {
  const returnArr: any[] = [];
  snapshot.forEach((childSnapshot: any) => {
    const item = childSnapshot.val();
    item.key = childSnapshot.key;
    returnArr.push(item);
  });
  return returnArr;
};

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})


export class RoomListComponent implements OnInit {
  nickname = '';
  displayedColumns: string[] = ['roomname'];
  rooms: any[] = [];
  isLoadingResults = true;

  constructor(private route: ActivatedRoute, private router: Router, public datepipe: DatePipe) {
    this.nickname = localStorage.getItem('nickname');
    firebase.database().ref('rooms/').once('value', resp => {
      this.rooms = [];
      this.rooms = snapshotToArray(resp);
      this.isLoadingResults = false;
    });
  }

  ngOnInit(): void {
  }

  enterChatRoom(roomname: string): void {
    const chat = {roomname: '', nickname: '', message: '', date: '', type: ''};
    chat.roomname = roomname;
    chat.nickname = this.nickname;
    const dateCreated = new Date();
    chat.date = this.datepipe.transform(dateCreated, 'dd/MM/yyyy HH:mm:ss');
    chat.message = `${this.nickname} вошел в комнату`;
    chat.type = 'join';
    const newMessage = firebase.database().ref('chats/').push();
    newMessage.set(chat);

    firebase.database().ref('roomusers/').orderByChild('roomname').equalTo(roomname).once('value', (resp: any) => {
      let roomuser: any[] = [];
      roomuser = snapshotToArray(resp);
      const user = roomuser.find(x => x.nickname === this.nickname);
      if (user !== undefined) {
        const userRef = firebase.database().ref('roomusers/' + user.key);
        userRef.update({status: 'online'});
      } else {
        const newroomuser = {roomname: '', nickname: '', status: ''};
        newroomuser.roomname = roomname;
        newroomuser.nickname = this.nickname;
        newroomuser.status = 'online';
        const newRoomUser = firebase.database().ref('roomusers/').push();
        newRoomUser.set(newroomuser);
      }
    });

    this.router.navigate(['lessons', 'chat', 'chatroom', roomname]);
  }

  logout(): void {
    localStorage.removeItem('nickname');
    this.router.navigate(['lessons', 'chat', 'login-chat']);
  }

}

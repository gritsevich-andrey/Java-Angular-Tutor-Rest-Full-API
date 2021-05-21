import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {WebsocketService} from '../shared/services/websocket.service';
import {ChatMessageDto} from '../shared/models/chatMessageDto';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-chat-page',
  templateUrl: './chat-page.component.html',
  styleUrls: ['./chat-page.component.css']
})
export class ChatPageComponent implements OnInit, OnDestroy {


  constructor(public webSocketService: WebsocketService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.webSocketService.openWebSocket();
  }


  ngOnDestroy(): void {
    this.webSocketService.closeWebSocket();
  }

  sendMessage(sendForm: NgForm): void {
    this.userService.getCurrentUser().subscribe(user => {
      const chatMessageDto = new ChatMessageDto(user.email, sendForm.value.message);
      this.webSocketService.sendMessage(chatMessageDto);
      sendForm.controls.message.reset();
    });
  }
}

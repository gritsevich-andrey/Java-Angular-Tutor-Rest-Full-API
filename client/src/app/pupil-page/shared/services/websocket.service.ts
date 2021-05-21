import {Injectable} from '@angular/core';
import {ChatMessageDto} from '../models/chatMessageDto';
@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  webSocket: WebSocket | undefined;
  chatMessages: ChatMessageDto[] = [];
constructor() {
}
  public openWebSocket(): void{
    this.webSocket = new WebSocket('ws://localhost:8086/chat');

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event);
    };

    this.webSocket.onmessage = (event) => {
      const chatMessageDto = JSON.parse(event.data);
      this.chatMessages.push(chatMessageDto);
    };

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event);
    };
  }
  public sendMessage(chatMessageDto: ChatMessageDto): void{
    this.webSocket.send(JSON.stringify(chatMessageDto));
  }

  public closeWebSocket(): void {
    this.webSocket.close();
  }
}

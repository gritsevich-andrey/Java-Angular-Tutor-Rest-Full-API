import * as SockJS from 'sockjs-client';
import {PrivateChatComponent} from './private-chat.component';
import * as Stomp from 'stompjs';

export class WebSocketAPI {

  webSocketEndPoint = 'http://localhost:8087/ws';
  topic = '/topic/greetings';
  stompClient: any;
  chatComponent: PrivateChatComponent | undefined;

  constructor() {
  }

  connect(): void {
    const socket = new SockJS('http://localhost:8087/ws');
    this.stompClient = Stomp.over(socket);

    // const _this = this;
    // this.stompClient.connect({}, function (frame: any) {
    //   _this.stompClient.subscribe('/topic/hi', function (hello: any) {
    //     // _this.showGreeting(JSON.parse(hello.body).greeting);
    //   });
    // });
  }

  disconnect(): void {
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error: string): void {
  }
  send(message: any): void {
  }

  onMessageReceived(message: any): void {
  }
}

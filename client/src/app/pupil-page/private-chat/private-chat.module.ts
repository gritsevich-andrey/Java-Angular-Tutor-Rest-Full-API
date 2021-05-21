
import { NgModule } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {PrivateChatComponent} from './private-chat.component';
import {LoginChatComponent} from './shared/components/login-chat/login-chat.component';
import {RoomListComponent} from './shared/components/room-list/room-list.component';
import {AddroomComponent} from './shared/components/addroom/addroom.component';
import {ChatroomComponent} from './shared/components/chatroom/chatroom.component';
import {PrivateChatRoutingModule} from './private-chat-routing.module';
import {MaterialModule} from '../../material-module';
import {ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from '../../shared/shared.module';

@NgModule({
  declarations: [
    LoginChatComponent,
    RoomListComponent,
    AddroomComponent,
    ChatroomComponent
  ],
    imports: [
        MaterialModule,
        PrivateChatRoutingModule,
        CommonModule,
        ReactiveFormsModule,
        SharedModule
    ],
  providers: [DatePipe],
  bootstrap: [PrivateChatComponent]
})
export class PrivateChatModule { }

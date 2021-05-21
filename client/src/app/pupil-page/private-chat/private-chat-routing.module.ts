import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginChatComponent} from './shared/components/login-chat/login-chat.component';
import {AddroomComponent} from './shared/components/addroom/addroom.component';
import {RoomListComponent} from './shared/components/room-list/room-list.component';
import {ChatroomComponent} from './shared/components/chatroom/chatroom.component';
import {PupilRoutingModule} from '../pupil-routing.module';



const routes: Routes = [
  { path: 'login-chat', component: LoginChatComponent },
  { path: 'roomlist', component: RoomListComponent },
  { path: 'addroom', component: AddroomComponent },
  { path: 'chatroom/:roomname', component: ChatroomComponent },
  { path: '',
    redirectTo: 'login-chat',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateChatRoutingModule { }

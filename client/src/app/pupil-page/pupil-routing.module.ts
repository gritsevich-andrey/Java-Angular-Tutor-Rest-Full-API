import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {PupilLayoutComponent} from './shared/components/pupil-layout/pupil-layout.component';
import {AuthGuardService} from '../helper/auth-guard.service';
import {OrderLessonsComponent} from './child-page/calendar-page/order-lessons.component';
import {FindInstructorComponent} from './child-page/find-instructor/find-instructor.component';
import {ProgramLayoutComponent} from './program-page/shared/components/program-layout/program-layout.component';
import {HistoryPageComponent} from './child-page/calendar-page/history-page/history-page.component';
import {CartPageComponent} from './cart-page/cart-page.component';
import {PupilProfileComponent} from './child-page/pupil-profile/pupil-profile.component';
import {MyLessonsPageComponent} from './child-page/my-lessons-page/my-lessons-page.component';
import {MyProgramPageComponent} from './child-page/my-program-page/my-program-page.component';
import {HistoryProgramComponent} from './child-page/calendar-page/history-page/history-program/history-program.component';
import {ChatPageComponent} from './chat-page/chat-page.component';

const routes: Routes = [
  {
    path: '', component: PupilLayoutComponent, canActivate: [AuthGuardService], children: [
      {path: 'instructors', component: FindInstructorComponent, canActivateChild: [AuthGuardService]},
      {path: 'cart', component: CartPageComponent, canActivateChild: [AuthGuardService]},
      {path: 'programs', component: ProgramLayoutComponent, canActivateChild: [AuthGuardService]},
      {path: 'my-lessons', component: MyLessonsPageComponent, canActivateChild: [AuthGuardService]},
      {path: 'my-programs', component: MyProgramPageComponent, canActivateChild: [AuthGuardService]},
      {
        path: 'history', component: OrderLessonsComponent, children: [
          {path: 'my-lesson', component: HistoryPageComponent, canActivateChild: [AuthGuardService]},
          {path: 'my-program', component: HistoryProgramComponent, canActivateChild: [AuthGuardService]}
        ]
      },
      {path: 'profile', component: PupilProfileComponent, canActivateChild: [AuthGuardService]},
      {path: 'all-chat', component: ChatPageComponent, canActivateChild: [AuthGuardService]},
      {path: 'chat', loadChildren: () => import('../pupil-page/private-chat/private-chat.module').then(m => m.PrivateChatModule)}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class PupilRoutingModule {
}

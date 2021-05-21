import {NgModule} from '@angular/core';
import {FindInstructorComponent} from './child-page/find-instructor/find-instructor.component';
import {RouterModule} from '@angular/router';
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {PupilLayoutComponent} from './shared/components/pupil-layout/pupil-layout.component';
import {OrderLessonsComponent} from './child-page/calendar-page/order-lessons.component';
import {MaterialModule} from '../material-module';
import {AuthGuardService} from '../helper/auth-guard.service';
import {HistoryPageComponent} from './child-page/calendar-page/history-page/history-page.component';
import {HistoryLessonComponent} from './child-page/calendar-page/history-page/history-list/history-lesson.component';
import {SharedModule} from '../shared/shared.module';
import {ProgramsComponent} from './program-page/program-page.component';
import {ProgramLayoutComponent} from './program-page/shared/components/program-layout/program-layout.component';
import {MatDialogModule} from '@angular/material/dialog';
import {PupilProfileComponent} from './child-page/pupil-profile/pupil-profile.component';

import {ProgramOrderComponent} from './program-page/program-categories/program-order/program-order.component';
import {SearchPipe} from './program-page/shared/pipes/search.pipe';
import {PupilRoutingModule} from './pupil-routing.module';
import {authInterceptorProviders} from '../helper/auth-interceptor.service';
import {authErrorInterceptorProvider} from '../helper/error-interceptor.service';
import {NgxPaginationModule} from 'ngx-pagination';
import {CartPageComponent} from './cart-page/cart-page.component';
import {LessonPipePipe} from './shared/pipes/lesson-pipe.pipe';
import {FilterPipe} from './program-page/shared/pipes/filter.pipe';
import {SearchInstructorPipe} from './program-page/shared/pipes/search-instructor.pipe';
import {ProgramInfoComponent} from './program-page/program-categories/program-order/program-info/program-info.component';
import {SearchNamePipe} from './child-page/calendar-page/history-page/history-list/shared/pipes/search-name.pipe';
import {MyLessonsPageComponent} from './child-page/my-lessons-page/my-lessons-page.component';
import {MyProgramPageComponent} from './child-page/my-program-page/my-program-page.component';
import {HistoryProgramComponent} from './child-page/calendar-page/history-page/history-program/history-program.component';
import {ChatPageComponent} from './chat-page/chat-page.component';
import {PrivateChatComponent} from './private-chat/private-chat.component';
import {FirebaseConfigModule} from './private-chat/firebase-config.module';

@NgModule({
  declarations: [
    PupilLayoutComponent,
    FindInstructorComponent,
    OrderLessonsComponent,
    HistoryPageComponent,
    HistoryLessonComponent,
    PupilProfileComponent,
    ProgramsComponent,
    ProgramLayoutComponent,
    ProgramOrderComponent,
    CartPageComponent,
    LessonPipePipe,
    SearchPipe,
    FilterPipe,
    SearchInstructorPipe,
    ProgramInfoComponent,
    SearchNamePipe,
    MyLessonsPageComponent,
    MyProgramPageComponent,
    HistoryProgramComponent,
    ChatPageComponent,
    PrivateChatComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    MaterialModule,
    MatDialogModule,
    PupilRoutingModule,
    NgxPaginationModule,
    FirebaseConfigModule
  ],
  providers: [AuthGuardService,
    authInterceptorProviders,
    authErrorInterceptorProvider,
    DatePipe
  ],
    exports: [RouterModule, FilterPipe]
})

export class PupilModule {

}

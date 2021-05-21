import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {LoginPageComponent} from './login-page/login-page.component';
import {DashboardPageComponent} from './dashboard-page/dashboard-page.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {AdminLayoutComponent} from './shared/components/admin-layot/admin-layout.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from '../shared/shared.module';
import {UsersPageComponent} from './users-page/users-page.component';
import {AuthGuardService} from '../helper/auth-guard.service';
import {MatDialogModule} from '@angular/material/dialog';
import {ProgramsComponent} from './programs-page/programs.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {SearchPipe} from './shared/pipes/search.pipe';
import {AdminProgSearchPipe} from './shared/pipes/admin-prog-search.pipe';
import {LessonPageComponent} from './lesson-page/lesson-page.component';
import {AdminLessonSearchPipe} from './shared/pipes/admin-lesson-search.pipe';
import {MaterialModule} from '../material-module';
import {CreateProgramComponent} from './programs-page/child-page/create-program/create-program.component';

@NgModule({
  declarations: [
    AdminLayoutComponent,
    LoginPageComponent,
    DashboardPageComponent,
    UsersPageComponent,
    CreateUserComponent,
    ProgramsComponent,
    SearchPipe,
    AdminProgSearchPipe,
    LessonPageComponent,
    AdminLessonSearchPipe,
    CreateProgramComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: AdminLayoutComponent, data: {roles: 'ADMIN'}, children: [
          {
            path: 'dashboard', component: DashboardPageComponent,
            data: {roles: 'ADMIN'}
          },
          {
            path: 'login', component: LoginPageComponent,
            data: {roles: ['ADMIN']}
          },
          {
            path: 'users', component: UsersPageComponent,
            data: {roles: 'ADMIN'}
          },
          {
            path: 'programs', component: ProgramsComponent,
            data: {roles: ['ADMIN']}
          },
          {
            path: 'lessons', component: LessonPageComponent,
            data: {roles: ['ADMIN']}
          }
        ]
      }
    ]),
    MatDialogModule,
    NgxPaginationModule,
    MaterialModule
  ],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [AuthGuardService]
})
export class AdminModule {

}

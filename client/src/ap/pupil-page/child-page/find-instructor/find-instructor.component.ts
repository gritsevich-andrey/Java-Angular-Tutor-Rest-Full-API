import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TokenStorageService} from '../../../services/token-storage.service';
import {LessonService} from '../../../services/lesson.sevice';
import {NotificationService} from '../../../services/notification.service';
import {Instructors, Lesson} from '../../../shared/interfaces';
import {environment} from '../../../../environments/environment';
import {UserService} from 'src/app/services/user.service';
import {Subscription} from 'rxjs';


@Component({
  selector: 'app-find-instructor',
  templateUrl: './find-instructor.component.html',
  styleUrls: ['./find-instructor.component.css']
})
export class FindInstructorComponent implements OnInit, OnDestroy {
  instructors: Instructors[] = [];
  pupilId: string | any;
  lesson: Lesson | any;
  userSub?: Subscription;

  constructor(private http: HttpClient, public ts: TokenStorageService,
              private lessonService: LessonService,
              private notificationService: NotificationService,
              private userService: UserService) {
  }

  ngOnInit(): void {

    this.http.get<Instructors[]>(environment.USER_API + '/instructors').subscribe(instructors => {
      this.instructors = instructors;
    });
  }

  registerLessonOrder(instructor: Instructors): any {
    this.userSub = this.userService.getCurrentUser().subscribe(user => {
      this.pupilId = user.userId;
      this.lessonService.registerLessonOrder(instructor, this.pupilId);
    }, error => {
      this.notificationService.showSnackBar(error.message = 'Ошибка регистрации урока');
    });
  }

  ngOnDestroy(): void {
  }
}

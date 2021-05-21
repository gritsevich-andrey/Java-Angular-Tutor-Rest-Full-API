import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LessonService} from '../../../services/lesson.sevice';
import {Lesson} from '../../../shared/interfaces';
import {UserService} from '../../../services/user.service';
import {NotificationService} from '../../../services/notification.service';

@Component({
  selector: 'app-my-lessons-page',
  templateUrl: './my-lessons-page.component.html',
  styleUrls: ['./my-lessons-page.component.css']
})
export class MyLessonsPageComponent implements OnInit {
  lessons: Lesson[] = [];
  pupilId?: string;
  lessonDate = '';
  searchInstructor = '';
  page = 1;
  searchCategory = '';
  programName = '';
  totalLength: number | any;
  reloading = false;

  constructor(private http: HttpClient,
              private lessonService: LessonService,
              private userService: UserService,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.userService.getCurrentUser()
      .subscribe(user => {
        this.pupilId = user.userId;
        this.lessonService.getLessonsById(this.pupilId)
          .subscribe(
            lessons => {
              this.lessons = lessons;
              this.reloading = true;
            }, error => {
              this.notificationService.showSnackBar(error.message = 'Ошибка загрузки уроков');
            }
          );
      });
  }
}


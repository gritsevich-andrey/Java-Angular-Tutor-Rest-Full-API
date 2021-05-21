import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Instructors, Lesson} from '../shared/interfaces';
import {NotificationService} from './notification.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LessonService {
  instructors: Instructors[] = [];

  constructor(private http: HttpClient,
              private notificationService: NotificationService) {
  }

  registerLessonOrder(lesson: Instructors, pupilId: string): void {
    this.http.post(environment.LESSON_ORDER_API, {
      instructorName: lesson.profile?.name,
      cost: 1500,
      instructorId: lesson.userId,
      pupilId
    })
      .subscribe(data => {
        this.notificationService.showSnackBar('Урок куплен');
        console.log(lesson.profile?.name);
      }, error => {
        console.log(error);
        this.notificationService.showSnackBar(error.message = 'Ошибка создания урока');
      });
  }
  getLessonsById(pupilId?: string): Observable<Lesson[]> {
      return this.http.get<Lesson[]>(environment.LESSONS_API + '/pupil/' + pupilId);
    }
}

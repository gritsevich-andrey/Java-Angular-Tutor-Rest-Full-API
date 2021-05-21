import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Lesson} from '../../shared/interfaces';
import {environment} from '../../../environments/environment';
import {NotificationService} from '../notification.service';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {
  constructor(private http: HttpClient,
              private notificationService: NotificationService) {
  }


  fetch(params: any = {}): Observable<Lesson[]> {
    return this.http.get<Lesson[]>(environment.LESSONS_API, {
      params: new HttpParams({
        fromObject: params
      })
    });
  }
  getLessons(): Observable<Lesson[]> {
    return  this.http.get<Lesson[]>(environment.LESSONS_API);
  }
  getLessonsByPupilId(pupilId: string): Observable<Lesson[]> {
    return this.http.get<Lesson[]>(environment.LESSONS_API + '/pupil/' + pupilId);
  }
}

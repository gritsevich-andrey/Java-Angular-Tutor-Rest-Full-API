import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Lesson} from '../../shared/interfaces';
import {HistoryService} from '../../services/history-service/history-service';

@Component({
  selector: 'app-lesson-page',
  templateUrl: './lesson-page.component.html',
  styleUrls: ['./lesson-page.component.css']
})
export class LessonPageComponent implements OnInit {
  lesson$: Observable<Lesson[]> | undefined;
  page: number | string = 1;
  totalLength: string | number = 11;
  lessonDate: string | any = '';

  constructor(private historyService: HistoryService) {
  }

  ngOnInit(): void {
    this.lesson$ = this.historyService.getLessons();
  }

}

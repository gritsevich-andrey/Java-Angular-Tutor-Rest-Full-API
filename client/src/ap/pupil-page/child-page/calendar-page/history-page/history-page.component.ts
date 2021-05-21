import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MaterialInstance} from '../../../../services/material.service';
import {HistoryService} from '../../../../services/history-service/history-service';
import {Subscription} from 'rxjs';
import {Filter, Lesson} from '../../../../shared/interfaces';
import {UserService} from '../../../../services/user.service';

const STEP = 2;

@Component({
  selector: 'app-history-page',
  templateUrl: './history-page.component.html',
  styleUrls: ['./history-page.component.css']
})
export class HistoryPageComponent implements OnInit {
  @ViewChild('tooltip') tooltipRef: ElementRef | any;
  tooltip: MaterialInstance | any;
  lessons: Lesson[] = [];
  reloading = false;
  pupilId: string | any;

  links = [
    {url: 'my-history', name: 'Заказы уроков'},
    {url: 'order', name: 'Заказы программ'}
  ];
  constructor(private historyService: HistoryService, private userService: UserService) {
  }

  ngOnInit(): void {
    this.reloading = true;
    this.userService.getCurrentUser()
      .subscribe(user => {
        this.pupilId = user.userId;
        this.historyService.getLessonsByPupilId(this.pupilId).subscribe(
          lessons => {
            this.lessons = this.lessons.concat(lessons);
            this.reloading = false;
          }
        );
      });
  }
}

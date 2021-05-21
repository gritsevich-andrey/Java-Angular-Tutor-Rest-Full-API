import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Lesson} from '../../../../../shared/interfaces';
import {MaterialInstance} from '../../../../../services/material.service';

@Component({
  selector: 'app-history-list',
  templateUrl: './history-lesson.component.html',
  styleUrls: ['./history-lesson.component.css']
})
export class HistoryLessonComponent implements OnInit {
  @Input() lessons?: Lesson[];
  @ViewChild('modal') modalRef: ElementRef | any;
  modal: MaterialInstance | any;
  selectedLesson: Lesson | any;
  totalLength?: number;
  page = 1;
  lessonDate: string | any = '';
  searchInstructor: string | any = '';

  constructor() {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.totalLength = this.lessons.length;
  }
}

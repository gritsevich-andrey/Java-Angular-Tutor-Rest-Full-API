import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import {MaterialService} from '../../../services/material.service';

@Component({
  selector: 'app-calendar-page',
  templateUrl: './order-lessons.component.html',
  styleUrls: ['./order-lessons.component.css']
})
export class OrderLessonsComponent implements AfterViewInit {
  @ViewChild('floating') floatingRef: ElementRef | undefined;
  links = [
    {url: 'my-history', name: 'Заказы уроков'},
    {url: 'order', name: 'Заказы программ'}
  ];
  ngAfterViewInit(): void {
    MaterialService.initializeFloatingButton(this.floatingRef);
  }
}

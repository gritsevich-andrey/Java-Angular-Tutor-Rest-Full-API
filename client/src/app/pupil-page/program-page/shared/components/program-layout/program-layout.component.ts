import {Component, OnInit} from '@angular/core';
import {CategoryProgram} from '../../../../../shared/interfaces';
import {ProgramService} from '../../services/program-service';
import {Observable} from 'rxjs';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-program-categories',
  templateUrl: './program-layout.component.html',
  styleUrls: ['./program-layout.component.css']
})
export class ProgramLayoutComponent implements OnInit {
  categories$: Observable<CategoryProgram[]> | undefined;
  form?: FormGroup | undefined;
  links = [
    {url: '/lessons/history/my-history', name: 'Заказы уроков'},
    {url: 'order', name: 'Заказы программ'}
  ];
  constructor(private programService: ProgramService) {
  }

  ngOnInit(): void {
    this.categories$ = this.programService.getCategory();
    this.form = new FormGroup({
      category: new FormControl()
    });
  }
}

import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ProgramService} from '../../pupil-page/program-page/shared/services/program-service';
import {Observable} from 'rxjs';
import {Program} from '../../shared/interfaces';

@Component({
  selector: 'app-programs-page',
  templateUrl: './program-page.component.html',
  styleUrls: ['./program-page.component.css']
})
export class ProgramsComponent implements OnInit {
  form: FormGroup | undefined;
  programs$: Observable<Program[]> | undefined;
  links = [
    {url: '/admin/users', name: 'Пользователи'},
    {url: '/admin/users', name: 'Уроки'}
  ];
  programName = '';

  constructor(private programService: ProgramService) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl(),
      imageSrc: new FormControl(),
      instructor: new FormControl(),
      cost: new FormControl(),
      category: new FormControl()
    });
    this.programs$ = this.programService.getPrograms();
  }

}

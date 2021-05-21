import {Component, OnInit} from '@angular/core';
import {ProgramService} from '../../../shared/services/program-service';
import {ActivatedRoute, Params} from '@angular/router';
import {Program} from '../../../../../shared/interfaces';
import {switchMap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {NotificationService} from '../../../../../services/notification.service';

@Component({
  selector: 'app-program-info',
  templateUrl: './program-info.component.html',
  styleUrls: ['./program-info.component.css']
})
export class ProgramInfoComponent implements OnInit {
  program: Observable<Program> | undefined;

  constructor(private programService: ProgramService,
              private route: ActivatedRoute,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => {
          return this.program = this.programService.getProgramById(params.id);
        })
      ).subscribe(() => {
        return of(true);
      }, error => {
        this.notificationService.showSnackBar(error.message = 'УПС. Ошибка загрузки программы');
        return of(false);
      }
    );
  }

  addProduct(program: Program): void {
    this.programService.addProduct(program);
    this.notificationService.showSnackBar('Программа добавлена в мои желания');
  }
}

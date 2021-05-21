import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {ProgramService} from '../../shared/services/program-service';
import {Observable} from 'rxjs';
import {Program} from '../../../../shared/interfaces';
import {NotificationService} from '../../../../services/notification.service';
import {UserService} from '../../../../services/user.service';

@Component({
  selector: 'app-program-order',
  templateUrl: './program-order.component.html',
  styleUrls: ['./program-order.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProgramOrderComponent implements OnInit {
  programs$: Observable<Program[]> | undefined;
  searchCategory = '';
  searchInstructor = '';
  programName = '';
  totalLength: number | any;
  page = 1;
  reloading = true;
  customerId?: string;

  constructor(private programService: ProgramService,
              private notificationService: NotificationService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.programs$ = this.programService.getPrograms();
    this.reloading = false;
  }

  addProduct(program: Program): void {
    this.programService.addProduct(program);
    this.notificationService.showSnackBar('Программа добавлена в мои желания');
  }

  buyProgram(program: Program): void {
    this.userService.getCurrentUser()
      .subscribe(user => {
        this.customerId = user.userId;
        this.programService.buyProgram(user.userId, program)
          .subscribe(() => {
            this.notificationService.showSnackBar('Программа куплена');
          }, error => {
              this.notificationService.showSnackBar(error.message = 'Ошибка покупки программы');
            }
          );
      });
  }

}

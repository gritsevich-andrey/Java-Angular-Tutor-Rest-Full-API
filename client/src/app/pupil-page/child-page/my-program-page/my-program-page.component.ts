import {Component, OnInit} from '@angular/core';
import {BuyingProgram} from '../../../shared/interfaces';
import {ProgramService} from '../../program-page/shared/services/program-service';
import {UserService} from '../../../services/user.service';
import {NotificationService} from '../../../services/notification.service';

@Component({
  selector: 'app-my-program-page',
  templateUrl: './my-program-page.component.html',
  styleUrls: ['./my-program-page.component.css']
})
export class MyProgramPageComponent implements OnInit {
  buyingProgram: BuyingProgram[] = [];
  page = 1;
  reloading = false;
  searchCategory = '';
  searchInstructor = '';
  programName = '';
  totalLength: number | any;

  constructor(private programService: ProgramService,
              private userService: UserService,
              private notificationService: NotificationService) {
  }
  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(user => {
       this.programService.getBuyingProgramByPupilId(user.id)
        .subscribe(program => {
          this.buyingProgram = program;
          this.totalLength = program.length;
          this.reloading = true;
        }, error => {
            this.notificationService.showSnackBar(error.message = 'Ошибка загрузки программ');
          }
        );
    });
  }

}

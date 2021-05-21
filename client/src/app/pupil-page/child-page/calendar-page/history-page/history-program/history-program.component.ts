import {Component, OnInit} from '@angular/core';
import {BuyingProgram} from '../../../../../shared/interfaces';
import {ProgramService} from '../../../../program-page/shared/services/program-service';
import {UserService} from '../../../../../services/user.service';

@Component({
  selector: 'app-history-program',
  templateUrl: './history-program.component.html',
  styleUrls: ['./history-program.component.css']
})
export class HistoryProgramComponent implements OnInit {
  buyingProgram: BuyingProgram[] = [];
  page = 1;
  searchCategory = '';
  searchInstructor = '';
  programName = '';
  totalLength: number | any;
  reloading = false;

  constructor(private programService: ProgramService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(user => {
      if (user) {
        this.programService.getBuyingProgramByPupilId(user.id)
          .subscribe(program => {
            this.buyingProgram = program;
            this.totalLength = program.length;
            this.reloading = true;
          });
      }
    });
  }

}

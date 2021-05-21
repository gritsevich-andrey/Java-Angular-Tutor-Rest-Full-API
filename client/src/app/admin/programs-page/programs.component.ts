import {Component, OnInit} from '@angular/core';
import {ProgramService} from '../../pupil-page/program-page/shared/services/program-service';
import {Observable} from 'rxjs';
import {Program} from '../../shared/interfaces';
import {NotificationService} from '../../services/notification.service';
import {CreateProgramComponent} from './child-page/create-program/create-program.component';
import {MatDialog} from '@angular/material/dialog';
@Component({
  selector: 'app-programs-page',
  templateUrl: './programs.component.html',
  styleUrls: ['./programs.component.css']
})
export class ProgramsComponent implements OnInit {
  programs$: Observable<Program[]> | undefined;
  programName = '';
program: Program[] = [];
  constructor(private programService: ProgramService,
              private notificationService: NotificationService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.programs$ = this.programService.getPrograms();
  }

  deleteProgram(id: string): void {
    this.programService.deleteProgram(id).subscribe(() =>
      // window.location.reload()
      console.log('Программа удалена')
    );
    this.notificationService.showSnackBar('Программа успешно удалена.');
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CreateProgramComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}

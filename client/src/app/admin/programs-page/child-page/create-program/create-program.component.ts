import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ProgramService} from '../../../../pupil-page/program-page/shared/services/program-service';
import {NotificationService} from '../../../../services/notification.service';

@Component({
  selector: 'app-create-program',
  templateUrl: './create-program.component.html',
  styleUrls: ['./create-program.component.css']
})
export class CreateProgramComponent implements OnInit {
  public form: FormGroup | any;

  constructor(private programService: ProgramService,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      category: new FormControl(null, [Validators.required]),
      imageSrc: new FormControl(null, [Validators.required]),
      description: new FormControl(null, [Validators.required]),
      instructorName: new FormControl(null, [Validators.required]),
      cost: new FormControl(null, [Validators.required])
    });
  }

  submit(): void {
    if (this.form.invalid) {
      return;
    }
    this.programService.createProgram({
      name: this.form.value.name,
      category: this.form.value.category,
      imageSrc: this.form.value.imageSrc,
      description: this.form.value.description,
      instructorName: this.form.value.instructorName,
      cost: this.form.value.cost
    }).subscribe(data => {
      this.notificationService.showSnackBar('Программа создана');
    }, error => {
      this.notificationService.showSnackBar(error.message = 'Ошибка создания программы');
    });
  }
}

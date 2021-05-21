import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NotificationService} from 'src/app/services/notification.service';
import {TokenStorageService} from 'src/app/services/token-storage.service';
import {UserService} from 'src/app/services/user.service';
import {UserProfile} from '../../../shared/interfaces';


@Component({
  selector: 'app-pupil-profile',
  templateUrl: './pupil-profile.component.html',
  styleUrls: ['./pupil-profile.component.css']
})
export class PupilProfileComponent implements OnInit {

  public profileForm!: FormGroup;
  private profile?: UserProfile;
  public isDescription = false;
  private email: string | any = '';

  constructor(
    private tokenStorage: TokenStorageService,
    private notificationService: NotificationService,
    private userService: UserService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {

    this.profileForm = this.createProfileForm();

    this.userService.getCurrentUser()
      .subscribe(data => {
        this.profileForm.get('email')?.setValue(this.email = data.email);
        this.profileForm.get('email')?.markAsTouched();

        this.profile = data.profile;

        if (data.roles && (data.roles.indexOf('INSTRUCTOR') != -1)) {
          this.isDescription = true;
        }

        if (this.profile) {
          if (this.profile.name) {
            this.profileForm.get('name')?.setValue(this.profile.name);
            this.profileForm.get('name')?.markAsTouched();
          }

          if (this.profile.surname) {
            this.profileForm.get('surname')?.setValue(this.profile.surname);
            this.profileForm.get('surname')?.markAsTouched();
          }

          if (this.profile?.tel) {
            this.profileForm.get('tel')?.setValue(this.profile.tel);
            this.profileForm.get('tel')?.markAsTouched();
          }

          if (this.isDescription) {

            if (this.profile.description) {
              this.profileForm.get('description')?.setValue(this.profile.description);
              this.profileForm.get('description')?.markAsTouched();
            }
          }
        }
      });


  }

  createProfileForm(): FormGroup {
    return this.fb.group({
      name: ['', []],
      surname: ['', []],
      email: [this.email, Validators.compose([Validators.required, Validators.email])],
      tel: ['', Validators.pattern('\\d+')],
      description: ['', []]
    });
  }


  // button hendler
  submit(): void {
    this.userService.updateUserProfile({
      userId: this.tokenStorage.getUser().userId,
      profile: {
        name: this.profileForm.value.name,
        surname: this.profileForm.value.surname,
        tel: this.profileForm.value.tel,
        description: this.profileForm.value.description
      }
    });
    this.notificationService.showSnackBar('Профиль успешно обновлен');
  }

}

import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  FormControl,
  Validators,
} from '@angular/forms';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { resetPd } from 'src/app/models/forgetPd.model';
import { ResetPdService } from 'src/app/services/reset-pd.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss'],
})
export class ResetPasswordComponent implements OnInit {
  userNewForm: FormGroup;
  faVisible = faEye;
  faHide = faEyeSlash;
  iconClick = true;
  iconClick1 = true;
  iconClick2 = true;
  submitted: boolean = false;
  messageDisplay: boolean;
  response: boolean;
  resetPdModel: resetPd = new resetPd();
  constructor(
    private formBuilder: FormBuilder,
    private resetPdService: ResetPdService
  ) {
    this.userNewForm = this.formBuilder.group(
      {
        // oldPassword: new FormControl('', [
        //   Validators.required,
        //   Validators.minLength(8),
        // ]),
        newPassword: new FormControl('', [
          Validators.required,
          Validators.minLength(8),
        ]),
        confirmPassword: new FormControl('', [Validators.required]),
      },
      {
        validators: this.MustMatch('newPassword', 'confirmPassword'),
      }
    );
  }
  get details() {
    // console.log(this.userNewForm.controls);
    return this.userNewForm.controls;
  }

  MustMatch(password: string, confirmPassword: string) {
    return (formGroup: FormGroup) => {
      const passwordControl = formGroup.controls[password];
      const confirmPasswordControl = formGroup.controls[confirmPassword];
      if (
        confirmPasswordControl.errors &&
        !confirmPasswordControl.errors.mustMatch
      ) {
        return;
      }
      if (passwordControl.value !== confirmPasswordControl.value) {
        confirmPasswordControl.setErrors({ mustMatch: true });
      } else {
        confirmPasswordControl.setErrors(null);
      }
    };
  }
  onSubmit() {
    this.submitted = true;
    if (this.userNewForm.invalid) {
      console.log('invalid');
      return;
    } else {
      console.log('valid');

      // this.resetPdModel.oldPassword = this.userNewForm.value.oldPassword;
      this.resetPdModel.newPassword = this.userNewForm.value.newPassword;
      this.submitted = false;
      this.userNewForm.reset();
      this.messageDisplay = true;
      this.resetPdService.postPasswordData(this.resetPdModel).subscribe(
        (res) => {
          console.log(res);
          this.response = true;
        },
        (err) => {
          // alert('something went wrong');
          this.response = false;
        }
        // (res) => {
        //   if (res.success) {
        //     alert(res.message);
        //   } else {
        //     alert(res.message);
        //   }
        // }
      );
    }
  }
  // onClick() {
  //   this.iconClick = !this.iconClick;
  // }
  onClick1() {
    this.iconClick1 = !this.iconClick1;
  }
  onClick2() {
    this.iconClick2 = !this.iconClick2;
  }
  successDisplay() {
    this.messageDisplay = false;
  }

  ngOnInit(): void {}
}

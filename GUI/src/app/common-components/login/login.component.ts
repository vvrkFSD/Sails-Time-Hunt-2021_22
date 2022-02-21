import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  lForm: FormGroup = new FormGroup({
    userData: new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, Validators.required),
    }),
  });
  constructor(private http: HttpClient) {}
  onSubmit(postData: { username: string; password: string }) {
    this.http
      .post(
        'https://timehunt-7db15-default-rtdb.asia-southeast1.firebasedatabase.app/login.json',
        postData
      )
      .subscribe((responseData) => {
        console.log(responseData);
      });
  }

  ngOnInit(): void {}
}

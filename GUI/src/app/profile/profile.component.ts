


import { Component, OnInit } from '@angular/core';

import { FormBuilder } from '@angular/forms';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {



  profileForm = new FormGroup({
    firstName: new FormControl('',Validators.required),
    lastName: new FormControl(''),
    phoneno:new FormControl(''),
    address:new FormControl(''),
    emailid:new FormControl(''),
    education:new FormControl(''),
    experience:new FormControl(''),
    oldpass:new FormControl(''),
    newpass:new FormControl(''),
    repass:new FormControl(''),
   
  });
  onSubmit() {
   
    console.warn(this.profileForm.value);
  }
  updateProfile() {
    this.profileForm.patchValue({
      firstName: 'Unknown',
      phoneno:'00000000000',
      address: {
        street: '00000 Street'
      }
    });
  }

  constructor( private http: HttpClient,) { }

  ngOnInit(): void {
    throw new Error("Method not implemented.");
  }

}



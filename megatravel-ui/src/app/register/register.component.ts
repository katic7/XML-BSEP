import { Component, OnInit } from '@angular/core';
import { SignUpInfo } from '../auth/forms/register-info';
import { AuthService } from '../auth/service/auth.service';
import { Location } from '@angular/common';
 

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
 
  constructor(private authService: AuthService, private location: Location) { }
 
  ngOnInit() { }
 
  onSubmit() {
    console.log(this.form);
 
    this.signupInfo = new SignUpInfo(
      this.form.name,
      this.form.username,
      this.form.email,
      this.form.password);
 
    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.table(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error;
        this.isSignUpFailed = true;
      }
    );
  this.location.replaceState("/login");
  window.location.reload();
  }
}
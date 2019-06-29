import { Component, OnInit } from '@angular/core';
import { SignUpInfo } from '../auth/forms/register-info';
import { AuthService } from '../auth/service/auth.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
 

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
  validEmail = false;
 
  constructor(private authService: AuthService, private location: Location, 
              private router: Router) { }
 
  ngOnInit() { }

  backToEmail() {
    this.validEmail = false;
  }

  signin() {
    this.router.navigate(['login']);
  }
 
  checkEmail() {
    this.authService.validEmail(this.form.username).subscribe(data => {
      this.validEmail = data;
    }, error => {
      this.validEmail = false;
    })
  }

  onSubmit() {
    console.log(this.form);

    if(this.form.password != this.form.rePassword){
      alert("Please, check entered passwords!");
      return;
    }
 
    this.signupInfo = new SignUpInfo(
      this.form.username,
      this.form.password,
      this.form.rePassword);
 
    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.table(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.router.navigate(['login']);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error;
        this.isSignUpFailed = true;
      }
    );
    
  }
}
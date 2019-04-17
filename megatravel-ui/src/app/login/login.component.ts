import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth/service/auth.service';
import { TokenStorageService } from '../auth/token-storage/token-storage.service';
import { AuthLoginInfo } from '../auth/forms/login-info';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { JWTAuth } from '../auth/response/jwt-auth';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  isEndpointOK = false;
  errorMessage = '';
  endpointError = "You are not authorized to access this resource.";
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
  private jwtauth: JWTAuth;
 
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private httpClient : HttpClient) { }
 
  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.authService.attemptAuth(this.loginInfo).subscribe(
        data => {
          console.table(data);
          this.jwtauth = data;
        });
    }
  }
 
  onSubmit() {
    console.log(this.form);
 
    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password);
 
    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        console.table(data);
        this.jwtauth = data;
        this.tokenStorage.saveToken(data.accessToken);
 
        this.isLoginFailed = false;
        this.isLoggedIn = true;
      },
      error => {
        this.errorMessage = error.error.errorMessage;
        this.isLoginFailed = true;
      }
    );
  }

}

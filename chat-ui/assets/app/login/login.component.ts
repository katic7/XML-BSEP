import { Component, OnInit } from '@angular/core';
 
//import { AuthService } from '../auth/service/auth.service';
//import { TokenStorageService } from '../auth/token-storage/token-storage.service';
//import { AuthLoginInfo } from '../auth/forms/login-info';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Location} from '@angular/common';
//import { JWTAuth } from '../auth/response/jwt-auth';
import { Router, NavigationEnd } from '@angular/router';
import { JWTAuth } from '../models/jwt-auth';
import { AuthLoginInfo } from '../models/login-info';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';
import { RouteService } from '../routeservice/RouteService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:[ AuthService, TokenStorageService, RouteService]
})
export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  isEndpointOK = false;
  errorMessage = '';
  validEmail = false;
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
  private jwtauth: JWTAuth;
  private element;

 
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,
              private httpClient : HttpClient, private _location: Location,
              private router: Router, private routerService : RouteService) { }
 
  ngOnInit() {
    
  }
  
  checkEmail() {
    this.authService.checkEmail(this.form.username).subscribe(data => {
      this.validEmail = true;
      this.isLoginFailed = false;

      
    }, error => {
      this.errorMessage = error.error.errorMessage;
      this.isLoginFailed = true;
    })
  }

  backToEmail() {
    this.validEmail = false;

  }

  ngAfterViewInit(){
   
      this.element = document.getElementById('em');
    
    
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
        
        console.log(this.routerService.getPreviousUrl());
        if(this.routerService.getPreviousUrl().includes("login")){
          window.history.back();
        }    
      },
      error => {
        this.errorMessage = "Wrong password, please try again."
        this.isLoginFailed = true;
      }
    );
  }

}

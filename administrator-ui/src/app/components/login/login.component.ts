import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth/service/auth.service';
import { TokenStorageService } from '../auth/token-storage/token-storage.service';
import { AuthLoginInfo } from '../auth/forms/login-info';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Location} from '@angular/common';
import { JWTAuth } from '../auth/response/jwt-auth';
import { Router, NavigationEnd } from '@angular/router';
import { AngularWaitBarrier } from 'blocking-proxy/built/lib/angular_wait_barrier';
import { RouteService } from '../routeservice/RouteService';

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
  validEmail = false;
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
  private jwtauth: JWTAuth;
  private element;

 
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,
              private httpClient : HttpClient, private _location: Location,
              private router: Router, private routeService: RouteService) { }
 
  ngOnInit() {
    
  }
 
  register() {
    this.router.navigate(['register']);
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
    console.log("prosli url " + this.routeService.getPreviousUrl());
    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password);
 
    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        console.table(data);
        if(data.profile.authorities[0] != "ROLE_ADMIN"){
          if(data.profile.authorities[0] != "ROLE_SYSTEM_ADMIN"){
            console.table(data);
            this.errorMessage = "You are not administrator!";
            this.isLoginFailed = true;
            return;
          }
          
        }
        console.table(data);
        this.jwtauth = data;
        this.tokenStorage.saveToken(data.accessToken);
 
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        
        this.router.navigate(['users']);       
      },
      error => {
        this.errorMessage = "Wrong password, please try again."
        this.isLoginFailed = true;
      }
    );
  }

}

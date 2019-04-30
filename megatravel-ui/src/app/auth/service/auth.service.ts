import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthLoginInfo } from '../forms/login-info';
import { SignUpInfo } from '../forms/register-info';
import { User } from 'src/app/models/User';
import { JWTAuth } from '../response/jwt-auth';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const authUrl = "http://localhost:8085/api/auth/"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = authUrl + 'signin';
  private signupUrl = authUrl + 'signup';
  private testLogin = authUrl + 'testSI';
  private emailCheck = authUrl + 'checkEmail/';
  private getLoggedUrl = authUrl + 'getLogged/';
  private validEmailUrl = authUrl + 'validEmail/';
  private confirmUserUrl = authUrl + 'confirm/';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JWTAuth> {
    return this.http.post<JWTAuth>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<any> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }

  checkEmail(email: String) :Observable<any> {
    return this.http.get(this.emailCheck+email);
  }

  getLogged(): Observable<any> {
    return this.http.get(this.getLoggedUrl + sessionStorage.getItem("AuthToken"));
  }
  
  validEmail(email: String) :Observable<any> {
    return this.http.get(this.validEmailUrl+email);
  }

  confirmUser(token: String) :Observable<any> {
    return this.http.get(this.confirmUserUrl+token);
  }

}
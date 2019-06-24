import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthLoginInfo } from '../models/login-info';
import { JWTAuth } from '../models/jwt-auth';


// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
// };

const authUrl = "https://localhost:8085/api/auth/"

@Injectable()
export class AuthService {

  private loginUrl = authUrl + 'signin';
  private signupUrl = authUrl + 'signup';
  private testLogin = authUrl + 'testSI';
  private emailCheck = authUrl + 'checkEmail/';
  private getLoggedUrl = authUrl + 'getLogged';
  private validEmailUrl = authUrl + 'validEmail/';
  private confirmUserUrl = authUrl + 'confirm/';
  private logoutUrl = authUrl + 'signout/';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JWTAuth> {
    return this.http.post<JWTAuth>(this.loginUrl, credentials);
  }

  checkEmail(email: String) :Observable<any> {
    let headers = new HttpHeaders({
      "Authorization" : "Bearer " + sessionStorage.getItem("AuthToken")});
    let options = { headers: headers };
    return this.http.get(this.emailCheck+email, options);
  }

  getLogged(): Observable<any> {
    let headers = new HttpHeaders({
      "Authorization" : "Bearer " + sessionStorage.getItem("AuthToken")});
    let options1 = { headers: headers };
    return this.http.get(this.getLoggedUrl, options1);
  }
  
  validEmail(email: String) :Observable<any> {
    let headers = new HttpHeaders({
      "Authorization" : "Bearer " + sessionStorage.getItem("AuthToken")});
    let options = { headers: headers };
    return this.http.get(this.validEmailUrl+email, options);
  }

  confirmUser(token: String) :Observable<any> {
    let headers = new HttpHeaders({
      "Authorization" : "Bearer " + sessionStorage.getItem("AuthToken")});
    let options = { headers: headers };
    return this.http.get(this.confirmUserUrl+token, options);
  }

  logout() :Observable<any> {
    let headers = new HttpHeaders({
      "Authorization" : "Bearer " + sessionStorage.getItem("AuthToken")});
    let options = { headers: headers };
    return this.http.get(this.logoutUrl, options);
  }

}
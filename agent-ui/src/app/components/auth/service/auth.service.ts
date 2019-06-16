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

const authUrl = "https://localhost:8085/api/auth/"

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private getCurrentUser = authUrl + 'getCurrentUser';
  private loginUrl = authUrl + 'signin';
  private signupUrl = authUrl + 'signup';
  private testLogin = authUrl + 'testSI';
  private emailCheck = authUrl + 'checkEmail/';
  private getLoggedUrl = authUrl + 'getLogged';
  private validEmailUrl = authUrl + 'validEmail/';
  private confirmUserUrl = authUrl + 'confirm/';
  private signout = authUrl + 'signout';
  private getAgent = authUrl + 'getOneAgent/';

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
    return this.http.get(this.getLoggedUrl);
  }
  
  validEmail(email: String) :Observable<any> {
    return this.http.get(this.validEmailUrl+email);
  }

  confirmUser(token: String) :Observable<any> {
    return this.http.get(this.confirmUserUrl+token);
  }

  getCurrent() : Observable<any>{
    return this.http.get(this.getCurrentUser);
  }

  signOut() : Observable<any>{
    return this.http.get(this.signout);
    window.sessionStorage.clear();
  }

  getOneAgent(id : number) : Observable<any>{
    return this.http.get(this.getAgent + id);
  }
}
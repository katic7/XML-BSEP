import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthLoginInfo } from '../forms/login-info';
import { JwtResponse } from '../response/jwt-response';
import { SignUpInfo } from '../forms/register-info';
import { User } from 'src/app/models/User';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const authUrl = "https://localhost:8443/api/auth/"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = authUrl + 'signin';
  private signupUrl = authUrl + 'signup';
  private testLogin = authUrl + 'testSI';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<any> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
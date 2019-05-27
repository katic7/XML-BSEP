import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ActivateUser } from 'src/app/models/ActivateUser';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http : HttpClient) { }

  getAllUsers() : Observable<any> {
    return this.http.get('//localhost:8085/api/auth/getAll');   //uskladiti
  }

  setUserActive(acU : ActivateUser) : Observable<any>{
    return this.http.post('//localhost:8085/api/auth/activateUser', acU);
  }

  deleteUser(id) : Observable<any> {
    return this.http.delete('//localhost:8085/api/auth/deleteUser/' + id);
  }
}

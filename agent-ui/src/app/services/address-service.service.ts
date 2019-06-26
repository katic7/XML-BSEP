import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddressServiceService {

  constructor(private http : HttpClient) { }

  getAddress(id:number) :Observable<any>{
    return this.http.get("https://localhost:8081/agentservice/api/accommodations/getAddress/"+id);
  }
}

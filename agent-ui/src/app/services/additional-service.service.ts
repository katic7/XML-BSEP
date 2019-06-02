import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdditionalServiceService {

  constructor(private http : HttpClient) { }

  getAllAdditionalServ() : Observable<any>{
    return this.http.get('http://localhost:8091/api/accommodations/allAdditionalServices');    //PRILAGODI BEKENND APIU
  }


}

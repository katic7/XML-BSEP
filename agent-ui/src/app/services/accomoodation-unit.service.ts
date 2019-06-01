import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccomoodationUnitService {

  constructor(private http : HttpClient) { }

  addNewAccU(accU : AccommodationUnit) : Observable<any>{
    return this.http.post('//localhost:8085/api/', accU);    //PRILAGODI BEKENND APIU
  }
}
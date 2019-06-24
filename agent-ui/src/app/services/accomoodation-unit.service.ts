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
    return this.http.post('https://localhost:8091/api/accommodations/addAccUnit', accU);    //PRILAGODI BEKENND APIU
  }

  getUnits() : Observable<any> {
    return this.http.get('https://localhost:8083/api/reservations/getUnits');
  }

}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SearchForm } from 'src/app/models/SearchForm';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  getFreeAccUnits(info : SearchForm) : Observable<any> {
    return this.http.post("http://localhost:8081/reservationservice/api/reservations/getfreeunits", info)
  }

}

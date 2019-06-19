import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SearchForm } from 'src/app/models/SearchForm';
import { HttpHeaders } from '@angular/common/http';
import { ReservationDTO } from 'src/app/models/ReservationDTO';

/*const headers = {
  "Access-Control-Allow-Origin" : "*"
}
const req = {
  head : new Headers(headers)
}*/
@Injectable({
  providedIn: 'root'
})

export class ReservationService {

  constructor(private http: HttpClient) { }
  

  getFreeAccUnits(info : SearchForm) : Observable<any> {
    let headers = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*" });
  let options = { headers: headers };
    //return this.http.post("http://localhost:8081/reservationservice/api/reservations/getfreeunits", info, options);
    return this.http.post("https://localhost:8083/api/reservations/getfreeunits", info);
  }

  getAdress(id) : Observable<any> {
    return this.http.get("https://localhost:8083/api/addresses/" + id);
  }

  makeAReservation(info : ReservationDTO) : Observable<any> {
    return this.http.post("https://localhost:8081/reservationservice/api/reservations", info);
  }

  getOneUnit(id) : Observable<any> {
    return this.http.get("https://localhost:8083/api/reservations/getOneUnit/" + id);
  }

  getReservationsByUser(id) : Observable<any> {
    return this.http.get("https://localhost:8081/reservationservice/api/reservations/byUser/" + id);
  }

  cancelReservation(id) : Observable<any> {
    return this.http.delete("https://localhost:8083/api/reservations/" + id);
  }

}

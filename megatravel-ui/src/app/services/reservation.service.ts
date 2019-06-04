import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SearchForm } from 'src/app/models/SearchForm';
import { HttpHeaders } from '@angular/common/http';

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
    return this.http.post("http://localhost:8083/api/reservations/getfreeunits", info);
  }

  getAdress(id) : Observable<any> {
    return this.http.get("http://localhost:8083/api/addresses/" + id);
  }

}

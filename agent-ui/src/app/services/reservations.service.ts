import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReservationCompletionResponse } from 'src/app/models/ReservationCompletionRequest';
import { Observable } from 'rxjs';
import { Reservations } from 'src/app/models/Reservations';
import { PostReservationBusynessRequest } from 'src/app/models/PostReservationBusynessRequest';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {

  constructor(private http : HttpClient) { }

  completeReservation(request: ReservationCompletionResponse) : Observable<any> {
    return this.http.post("https://localhost:8081/agentservice/api/reservations/completeReservation", request);
  }

  getReservationsForCompletion(date: Date) : Observable<any> {
    return this.http.post("https://localhost:8083/api/reservations/getForCompletion", date);
  }

  makeUnitBusy(res : PostReservationBusynessRequest) : Observable<any> {
    return this.http.post("https://localhost:8081/agentservice/api/reservations/makeUnitBusy", res);
  }

}

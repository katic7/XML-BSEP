import { Component, OnInit } from '@angular/core';
import { ReservationsService } from 'src/app/services/reservations.service';
import { Reservations } from 'src/app/models/Reservations';
import { DatePipe } from '@angular/common';
import { ReservationCompletionResponse } from 'src/app/models/ReservationCompletionRequest';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  reservations : Array<Reservations> = new Array<Reservations>();
  todaysDate = new Date();


  Complete(res) {
    var info : ReservationCompletionResponse = new ReservationCompletionResponse();
    info.completed = !res.completed;
    info.reservationID = res.id;
    console.log(info);

    this.reservationService.completeReservation(info).subscribe(data => { res.completed = !res.completed; });
  }


  constructor(private reservationService: ReservationsService, private pipe: DatePipe) { }

  ngOnInit() {
    this.todaysDate = new Date(this.pipe.transform(this.todaysDate, "yyyy-MM-dd"));
    this.reservationService.getReservationsForCompletion(this.todaysDate).subscribe( data => {
      console.log(data);
      this.reservations = data;
      this.reservations.forEach(r => {
        r.beginDate = r.beginDate.substring(0, 10);
        r.endDate = r.endDate.substring(0, 10);
      })
    } );
  }

}

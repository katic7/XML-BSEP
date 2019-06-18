import { Component, OnInit, Input } from '@angular/core';
import { ReservationDTO } from 'src/app/models/ReservationDTO';
import { User } from 'src/app/models/User';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { AccommodationunitService } from 'src/app/services/accommodationunit.service';
import { Address } from 'src/app/models/Address';
import { ReservationService } from 'src/app/services/reservation.service';
import { DatePipe } from '@angular/common';
import { EventEmitter } from '@angular/core';
import { Output } from '@angular/core';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  @Input() reservation : ReservationDTO;
  @Output() cancelingReservation = new EventEmitter<ReservationDTO>();
  @Input() logged : User;
  accUnit : AccommodationUnit = new AccommodationUnit();
  address : Address = new Address();
  indicator : boolean = false;
  todaysDate = new Date();

  constructor(private pipe: DatePipe, private reservationService: ReservationService, private accommodationUnitService : AccommodationunitService) { }

  cancelReservation() {
    this.reservationService.cancelReservation(this.reservation.id).subscribe(data =>  {alert("Reservation canceled.");      
      this.cancelingReservation.emit(this.reservation);
    });
  }

  ngOnInit() {
    console.log(this.reservation);
    this.todaysDate = this.pipe.transform(this.todaysDate, "yyyy-MM-dd");
    this.reservation.beginDate = this.pipe.transform(this.reservation.beginDate, "yyyy-MM-dd");
    this.reservation.endDate = this.pipe.transform(this.reservation.endDate, "yyyy-MM-dd");

    if(this.todaysDate < this.reservation.beginDate) {
      this.indicator = true;      
    }
    console.log(this.indicator);

    this.accommodationUnitService.getOne(this.reservation.accommodationUnitId).subscribe(data => { this.accUnit = data; console.log(data);
      this.reservationService.getAdress(this.accUnit.accommodationObject.addressId).subscribe(data => { this.address = data;});
     }); 
  }

}

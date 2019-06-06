import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationDTO } from 'src/app/models/ReservationDTO';
import { ReservationService } from 'src/app/services/reservation.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { AccommodationunitService } from 'src/app/services/accommodationunit.service';
import { Address } from 'src/app/models/Address';

@Component({
  selector: 'app-bookpage',
  templateUrl: './bookpage.component.html',
  styleUrls: ['./bookpage.component.css']
})
export class BookpageComponent implements OnInit {

  reservation : ReservationDTO = new ReservationDTO();
  accUnit: AccommodationUnit = new AccommodationUnit();
  address: Address = new Address();

  book() {
    this.reservationService.makeAReservation(this.reservation).subscribe(data => { console.log(data) });
  }

  constructor(private route: ActivatedRoute, private reservationService: ReservationService) { }

  ngOnInit() {

    this.route.params.forEach(a => {
      this.reservation.beginDate = a.in;
      this.reservation.endDate = a.out;
      this.reservation.userId = a.user;
      this.reservation.accommodationUnitId = a.acu;
      this.reservation.price = a.price;
      console.log(this.reservation);

      this.reservationService.getOneUnit(a.acu).subscribe(data => {
        this.accUnit = data;
        this.reservationService.getAdress(this.accUnit.accommodationObject.addressId).subscribe(data => { this.address = data });
      });
    });
  }

}

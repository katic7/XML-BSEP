import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationDTO } from 'src/app/models/ReservationDTO';
import { ReservationService } from 'src/app/services/reservation.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { AccommodationunitService } from 'src/app/services/accommodationunit.service';
import { Address } from 'src/app/models/Address';
import {Location} from '@angular/common';
import { RatingDTO } from '../models/RatingDTO';
import { CommentShow } from '../models/CommentShow';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-bookpage',
  templateUrl: './bookpage.component.html',
  styleUrls: ['./bookpage.component.css']
})
export class BookpageComponent implements OnInit {

  reservation : ReservationDTO = new ReservationDTO();
  accUnit: AccommodationUnit = new AccommodationUnit();
  address: Address = new Address();
  listOfRatings: RatingDTO[] = [];
  commentList: CommentShow[] = [];

  book() {
    this.reservationService.makeAReservation(this.reservation).subscribe(data => { console.log(data);
      alert("Booked successfully");
      this._location.back();});
  }

  goBack() {
    this._location.back();
  }

  constructor(private route: ActivatedRoute, private reservationService: ReservationService, 
    private _location: Location, private auth: AuthService) { }

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
        //this.reservationService.getAdress(this.accUnit.accommodationObject.address).subscribe(data => { this.address = data });
      });
    });

    this.reservationService.getPublishedCommentsOfAccommodation(this.reservation.accommodationUnitId).subscribe(data => {
        this.listOfRatings = data;
        this.listOfRatings.forEach(lr=> {
          this.auth.getOne(lr.userID).subscribe(data => {
            let com = new CommentShow();
            com.username = data.name + ' ' + data.surname;
            com.rating = lr.rating;
            com.comment = lr.comment;
            this.commentList.push(com);
          })
        })
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { Input } from '@angular/core';
import { SearchForm } from 'src/app/models/SearchForm';

import { ReservationService } from 'src/app/services/reservation.service';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Address } from 'src/app/models/Address';
import { ReservationDTO } from 'src/app/models/ReservationDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-searchobject',
  templateUrl: './searchobject.component.html',
  styleUrls: ['./searchobject.component.css']
})
export class SearchobjectComponent implements OnInit {

  @Input()  accUnit : AccommodationUnit
  @Input()  searchForm : SearchForm
  address : Address = new Address
  logged : User;
  difference


  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      this.logged = data;
    }, error => {
      this.logged = null;
    })
  }

  makeAReservation() {
    // let res : ReservationDTO = new ReservationDTO;
    // res.accommodationUnitId = this.accUnit.id;
    // res.beginDate = this.searchForm.checkin;
    // res.endDate = this.searchForm.checkout;
    // res.price = this.accUnit.price.price;
    // res.userId = this.logged.id;

    // console.log(res);

    this.router.navigate(['/book', {in: this.searchForm.checkin, out: this.searchForm.checkout, user: this.logged.id, acu: this.accUnit.id, price: this.accUnit.price.price}]);
  }


  constructor(private authService: AuthService, private reservationService: ReservationService, private router: Router) { }

  ngOnInit() {
    this.getLoggedUser();
    this.reservationService.getAdress(this.accUnit.accommodationObject.addressId).subscribe(data => { this.address = data;});
    var dateone:any = new Date(this.searchForm.checkin);
    var datetwo:any = new Date(this.searchForm.checkout);
    this.difference = (datetwo - dateone) / 1000 / 60 / 60 / 24;
  }

}
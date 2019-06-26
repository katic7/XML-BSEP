import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/components/auth/service/auth.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { AccomoodationUnitService } from 'src/app/services/accomoodation-unit.service';
import { FormControl } from '@angular/forms';
import { Reservations } from 'src/app/models/Reservations';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { ReservationsService } from 'src/app/services/reservations.service';
import { PostReservationBusynessRequest } from 'src/app/models/PostReservationBusynessRequest';

@Component({
  selector: 'app-newreservation',
  templateUrl: './newreservation.component.html',
  styleUrls: ['./newreservation.component.css']
})
export class NewreservationComponent implements OnInit {

  logged;
  accUnits : AccommodationUnit[] = [];
  todaysDate = new Date();

  form: any = {};
  
  beginDate = new FormControl('');
  endDate = new FormControl('');
  chosenAccUnit = new FormControl('');


  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      this.logged = data;
    }, error => {
      this.logged = null;
      alert("Morate biti ulogovani!")
      this.router.navigate(['login']);
    })
  }

  Cancel(){
    this.router.navigate(['/home']);  
  }


  onSubmit() {
    if(this.beginDate.value != '' && this.endDate.value != '' && this.chosenAccUnit.value != '' && this.beginDate.value != null && this.endDate.value != null && this.chosenAccUnit.value != null) {
      var res : PostReservationBusynessRequest = new PostReservationBusynessRequest();
      res.reservation = new Reservations();
      res.reservation.beginDate = this.datePipe.transform(this.beginDate.value,'yyyy-MM-dd');
      res.reservation.endDate = this.datePipe.transform(this.endDate.value,'yyyy-MM-dd');
      res.reservation.accUnitId = this.chosenAccUnit.value;
      res.reservation.userID = this.logged.id;
      res.reservation.active = false;
      res.reservation.completed = true;
      res.reservation.id = 9999;
      res.reservation.price = 0;
      res.reservation.reservationDate = this.datePipe.transform(this.todaysDate, "yyyy-MM-dd");
      //alert('ok');
      console.log(res);
      this.reservationService.makeUnitBusy(res).subscribe(data => {});
    } else {
      alert('Popuni sva polja.');
    }
    
  }

  constructor(private reservationService: ReservationsService, private datePipe: DatePipe, private router: Router, private authService: AuthService, private accommodationService: AccomoodationUnitService) { }

  ngOnInit() {
    this.accommodationService.getUnits().subscribe(data => { this.accUnits = data; console.log(data); });
    this.getLoggedUser();
  }

}

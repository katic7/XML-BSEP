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
import { FormControl } from '@angular/forms';
import { RatingDTO } from '../models/RatingDTO';
import { Router } from '@angular/router';
import { Agent } from '../models/Agent';

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
  indicator2 : boolean = false;
  todaysDate = new Date();
  already :boolean = false;
  comment = new FormControl('');
  rating = new FormControl('');
  allAgents : Agent[] = [];
  difference;
  constructor(private pipe: DatePipe, private reservationService: ReservationService,
     private accommodationUnitService : AccommodationunitService, private router : Router) { }

  cancelReservation() {
    this.reservationService.cancelReservation(this.reservation.id).subscribe(data =>  {alert("Reservation canceled.");      
      this.cancelingReservation.emit(this.reservation);
    });
  }

  ngOnInit() {
    if(this.logged == null || this.logged == undefined) {
      this.router.navigate(['/login']);
    }
    

    this.reservationService.getOneUnit(this.reservation.accommodationUnitId).subscribe(data2 => { this.accUnit = data2; console.log(data2);
      //this.reservationService.getAdress(this.accUnit.accommodationObject.addressId).subscribe(data => { this.address = data;});
      console.log(this.reservation);
      this.todaysDate = new Date(this.pipe.transform(this.todaysDate, "yyyy-MM-dd"));
      this.reservation.beginDate = this.pipe.transform(this.reservation.beginDate, "yyyy-MM-dd");
      this.reservation.endDate = this.pipe.transform(this.reservation.endDate, "yyyy-MM-dd");

      var dateone:any = new Date(this.todaysDate);
      var datetwo:any = new Date(this.reservation.beginDate);
      this.difference = (datetwo - dateone) / 1000 / 60 / 60 / 24; 
      console.log(this.difference);
      // if(this.todaysDate < new Date(this.reservation.beginDate)) {
      //   this.indicator = true;      
      // }
      if(this.difference - this.accUnit.accommodationObject.daysToCancel > 0) {
        this.indicator = true;      
      }

      if(this.difference > 0) {
        this.indicator = true;
        if(this.difference > this.accUnit.accommodationObject.daysToCancel) {
          this.indicator2 = true;
        } else {
          this.indicator2 = false;
        }
      } else {
        this.indicator = false; 
      }
      console.log(this.indicator);
     }); 

    console.log(this.accUnit);

  }

  Rate() {
    let rating = new RatingDTO();
    rating.accommodationID = this.accUnit.id;
    rating.userID = this.logged.id;
    rating.comment = this.comment.value;
    if(rating.comment.includes("<")){
      alert("XSS alert");
      return;
    }
    rating.rating = +this.rating.value;
    rating.reservationID = this.reservation.id;
    rating.published = false;
    this.reservationService.getRatingsFromAcc(this.accUnit.id).subscribe(data=> {
      let ratings:RatingDTO[] = data;
      for(var i=0;i<ratings.length;i++) {
        if(ratings[i].reservationID == this.reservation.id){
          alert("You have already rated this reservation!");
          return;
        }
      }
      this.reservationService.postRating(rating).subscribe(data => {
      
      })
    })
 
    this.comment = new FormControl('');
    this.rating = new FormControl('');
    
  }

  agentID:number;
  Chat(){
    this.accommodationUnitService.getAllAgents().subscribe(data =>{
      this.allAgents = data;
      for(var i=0; i<this.allAgents.length; i++){
        if(this.allAgents[i].accObj.id == this.accUnit.accommodationObject.id){
          this.agentID = this.allAgents[i].id;
          window.open('http://localhost:3000/chat/' + this.logged.id + "/" + this.reservation.id + "/" + this.agentID, "_blank");
        }
      }
    });  
  }

}

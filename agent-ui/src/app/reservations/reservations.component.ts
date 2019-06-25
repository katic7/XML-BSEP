import { Component, OnInit } from '@angular/core';
import { ReservationsService } from 'src/app/services/reservations.service';
import { Reservations } from 'src/app/models/Reservations';
import { DatePipe } from '@angular/common';
import { ReservationCompletionResponse } from 'src/app/models/ReservationCompletionRequest';
import { AuthService } from '../components/auth/service/auth.service';
import { User } from '../models/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  reservations : Array<Reservations> = new Array<Reservations>();
  upcomingRes : Array<Reservations> = new Array<Reservations>();
  todaysDate = new Date();
  logged:User;

  Complete(res) {
    var info : ReservationCompletionResponse = new ReservationCompletionResponse();
    info.completed = !res.completed;
    info.reservationID = res.id;
    console.log(info);

    this.reservationService.completeReservation(info).subscribe(data => { res.completed = !res.completed; });
  }


  constructor(private reservationService: ReservationsService, private pipe: DatePipe,private authService:AuthService,private router:Router) { }

  ngOnInit() {
   /* this.todaysDate = new Date(this.pipe.transform(this.todaysDate, "yyyy-MM-dd"));
    this.reservationService.getReservationsForCompletion(this.todaysDate).subscribe( data => {
      console.log(data);
      this.reservations = data;
      this.reservations.forEach(r => {
        r.beginDate = r.beginDate.substring(0, 10);
        r.endDate = r.endDate.substring(0, 10);
      })
    } );*/

    this.authService.getLogged().subscribe(data=>{
      this.logged = data;
      console.log(data.roles[0].name + "AAAAAAAAAAAAA");
      if(data.roles[0].name == "ROLE_AGENT"){
        this.authService.getOneAgent(this.logged.id).subscribe(agnet=>{
          if(agnet.accObj == null){
            this.router.navigate(['newObject']);
          }else{
           this.reservationService.getObjectReservations(agnet.accObj.id).subscribe(reserv=>{
             this.reservations = reserv;
             console.table(reserv);
           });
           this.reservationService.upcomingReservations(agnet.accObj.id).subscribe(res=>{
             this.upcomingRes = res;
             console.table(res);
           })
        }
      })
      }else{
        this.authService.logout();
        alert("Nemate rolu agent!");
        this.router.navigate(['login']);
      }
    }, error=>{
      alert("Morate biti ulogovani!")
      this.router.navigate(['login']);
    });
  }
}



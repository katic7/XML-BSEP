import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { User } from '../models/User';
import { ChatService } from '../chat.service';


@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private authService : AuthService, private router2: Router, private _chatService:ChatService, private router : ActivatedRoute) { }

  logged: User;
  room :string;

  ngOnInit() {
    this.room = this.router.snapshot.params.user + this.router.snapshot.params.reservationID + this.router.snapshot.params.agent;
    this.getLoggedUser();
  }

  
  /*
  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      this.logged = data;
    }, error => {
      this.logged = null;
    })
  }
  */

  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      if(data != null) {
        this.logged = data;
      }else{
        this.router2.navigate(['/login']);
      }
    }, error => {
      this.router2.navigate(['/login']);
      })
  }
  

  gotoProfile() {
    this.router2.navigate(['profile']);
  }

  leave(){
    this._chatService.leaveRoom({user:this.logged.name, room:this.room});
  }

  logout() {
    this.authService.logout().subscribe(data =>{
      this.leave();
    });
    sessionStorage.clear();
    this.router2.navigate(['/login']);
  }

}

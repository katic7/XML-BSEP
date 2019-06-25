import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { User } from '../models/User';


@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private authService : AuthService, private router: Router) { }

  logged: User;

  ngOnInit() {
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
        this.router.navigate(['/login']);
      }
    }, error => {
      this.router.navigate(['/login']);
      })
  }
  

  gotoProfile() {
    this.router.navigate(['profile']);
  }

  logout() {
    this.authService.logout().subscribe(data =>{

    });
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }

}

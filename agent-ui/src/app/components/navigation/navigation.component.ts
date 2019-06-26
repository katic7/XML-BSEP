import { Component, OnInit, Input } from '@angular/core';
import { AuthService } from '../auth/service/auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { Agent } from 'src/app/models/Agent';
import { AccommodationObject } from 'src/app/models/AccommodationObject';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private authService : AuthService, private router: Router) { }
  
  logged: User;
  agent: Agent;
  accObject : AccommodationObject = new AccommodationObject();
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
  }*/

  
  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      if(data != null) {
        this.logged = data;
        this.authService.getOneAgent(data.id).subscribe(agnet=>{
          this.agent = agnet;
          this.accObject = agnet.accObj;
        });
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
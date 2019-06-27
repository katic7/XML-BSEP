import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { AuthService } from '../auth/service/auth.service';
import { Router } from '@angular/router';
import { Role } from '../models/Role';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  logged: User;
  adminLogged : boolean = false;
  agentLogged : boolean = false;

  constructor(private authService : AuthService, private router: Router) { }

  ngOnInit() {
    this.getLoggedUser();
    
  }

  adminPage(){
    window.open('https://localhost:4201/home', "_blank");
  }

  agentPage(){
    window.open('https://localhost:4203/home', "_blank");
  }

  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      this.logged = data;
      if(this.logged.roles[0].name.valueOf().toString() == "ROLE_ADMIN" || this.logged.roles[0].name.valueOf().toString() == "ROLE_SYSTEM_ADMIN"){
        alert("admin");
        this.adminLogged = true;
        this.agentLogged = false;
      }else if(this.logged.roles[0].name.valueOf().toString() == "ROLE_AGENT"){
        alert("agent");
        this.agentLogged = true;
        this.adminLogged = false;
      }
    }, error => {
      this.logged = null;
    })
  }

  gotoProfile() {
    this.router.navigate(['profile']);
  }

  logout() {
    this.authService.logout().subscribe(data =>{
      this.adminLogged = false;
      this.agentLogged = false;
    });
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}

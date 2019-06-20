import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { AuthService } from '../auth/service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  logged: User;
  constructor(private authService : AuthService, private router: Router) { }

  ngOnInit() {
    this.getLoggedUser();
  }


  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      this.logged = data;
    }, error => {
      this.logged = null;
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

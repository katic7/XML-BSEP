import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { AuthService } from '../auth/service/auth.service';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  indicator : boolean;
  logged: User;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.authService.getLogged().subscribe(data=> {
      this.logged = data;
    }, error => {
      this.router.navigate(['login']);
    })

  }

  dashboard() {
    this.indicator = false;
  }

  bookings() {
    this.indicator = true;
  }

}

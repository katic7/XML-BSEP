import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { AuthService } from '../auth/service/auth.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Rating } from '../models/Rating';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  imbyte;
  indicator : boolean;
  logged: User;
  ratting: Rating;

  constructor(private authService: AuthService, private router: Router, 
              private imService: ImageService) { }

  ngOnInit() {
    this.authService.getLogged().subscribe(data=> {
      this.logged = data;
    }, error => {
      this.router.navigate(['login']);
    })
 
    this.imService.getProfilePicture().subscribe(data => {
      this.imbyte = data;
    }, error => {

    })
  }

  dashboard() {
    this.indicator = false;
  }

  bookings() {
    this.indicator = true;
  }

}

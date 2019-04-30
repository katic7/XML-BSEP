import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { AuthService } from '../auth/service/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  
  constructor(private authService : AuthService, private route: ActivatedRoute) { }

  ngOnInit() {
    if(this.route.snapshot.params.token != 'ft') {
      const token = this.route.snapshot.params.token;
      this.authService.confirmUser(token).subscribe(data => {

      }, error => {

      })
    } else {
      alert("verifikuj acc")
    }
  }

  

}

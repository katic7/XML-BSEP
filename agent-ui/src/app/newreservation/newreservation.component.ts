import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/components/auth/service/auth.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { AccomoodationUnitService } from 'src/app/services/accomoodation-unit.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-newreservation',
  templateUrl: './newreservation.component.html',
  styleUrls: ['./newreservation.component.css']
})
export class NewreservationComponent implements OnInit {

  logged;
  accUnits : AccommodationUnit[] = [];


  form: any = {};
  
  beginDate = new FormControl('');
  endDate = new FormControl('');
  chosenAccUnit = new FormControl('');


  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      this.logged = data;
    }, error => {
      this.logged = null;
    })
  }

  Cancel() {

  }

  onSubmit() {
    
  }

  constructor(private authService: AuthService, private accommodationService: AccomoodationUnitService) { }

  ngOnInit() {
    this.accommodationService.getUnits().subscribe(data => { this.accUnits = data; console.log(data); });
    this.getLoggedUser();
  }

}

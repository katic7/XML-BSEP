import { Component, OnInit } from '@angular/core';
import { SearchForm } from '../models/SearchForm';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservation-form',
  templateUrl: './reservation-form.component.html',
  styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit {

  destination = new  FormControl("");
  checkin = new  FormControl("");
  checkout = new  FormControl("");
  persons = new  FormControl("");

  constructor(private router: Router) { }

  ngOnInit() {
  }

  search() {
    const form : SearchForm = new SearchForm();
    form.checkin = this.checkin.value;
    form.checkout = this.checkout.value;
    form.destination = this.destination.value;
    form.persons = this.persons.value;

    if(form.destination.includes("<")){
      alert("XSS alert");
      return;
    }
    
    if(form.checkin != null || form.checkout != null || form.destination != '' || form.persons != null) {
      this.router.navigate(['/search',{ where: form.destination, in: form.checkin, out: form.checkout, persons: form.persons }])
    }
    
  }

}

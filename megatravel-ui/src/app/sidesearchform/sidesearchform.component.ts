import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchForm } from '../models/SearchForm';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidesearchform',
  templateUrl: './sidesearchform.component.html',
  styleUrls: ['./sidesearchform.component.css']
})
export class SidesearchformComponent implements OnInit {

  destination = new  FormControl("");
  checkin = new FormControl(new Date());
  checkout = new FormControl(new Date());
  persons = new  FormControl("");

  @Input() searchForm : SearchForm;

  constructor(private router: Router) { }

  ngOnInit() {
    this.destination.setValue(this.searchForm.destination);
    this.checkin.setValue(new Date(this.searchForm.checkin));
    this.checkout.setValue(new Date(this.searchForm.checkout));
    this.persons.setValue(this.searchForm.persons);
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

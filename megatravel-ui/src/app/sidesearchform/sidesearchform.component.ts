import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchForm } from '../models/SearchForm';

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

  constructor() { }

  ngOnInit() {
    this.destination.setValue(this.searchForm.destination);
    this.checkin.setValue(new Date(this.searchForm.checkin));
    this.checkout.setValue(new Date(this.searchForm.checkout));
    this.persons.setValue(this.searchForm.persons);
  }

  search() {

  }

}

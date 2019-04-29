import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { SearchForm } from '../models/SearchForm';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {

  searchForm : SearchForm = new SearchForm();
  numbers = [1,2,3,4,5,6,7,8,9,10];
  
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {

    this.route.params.forEach(a => {
      this.searchForm.checkin = a.in;
      this.searchForm.checkout = a.out;
      this.searchForm.destination = a.where;
      this.searchForm.persons = +a.persons;
   })

  
  }

}

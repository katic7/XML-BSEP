import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { SearchForm } from '../models/SearchForm';
import { ReservationService } from 'src/app/services/reservation.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { DatePipe } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';
import { AdditionalservicesService } from '../services/additionalservices.service';
import { AdditionalService } from '../models/AdditionalService';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {

  searchForm : SearchForm = new SearchForm();
  numbers = [1,2,3,4,5,6,7,8,9,10];
  accUnits : AccommodationUnit[] = [];
  searchTerm: AdditionalService = null;
  
  constructor(private route: ActivatedRoute, private reservationService: ReservationService, private pipe: DatePipe,
    private spinner: NgxSpinnerService, private addService: AdditionalservicesService) { }

  ngOnInit() {

    this.route.params.forEach(a => {
      this.searchForm.checkin = this.pipe.transform(a.in, "yyyy-MM-dd");
      this.searchForm.checkout = this.pipe.transform(a.out, "yyyy-MM-dd");
      this.searchForm.destination = a.where;
      this.searchForm.persons = +a.persons;
      console.log(this.searchForm);
      this.reservationService.getFreeAccUnits(this.searchForm).subscribe(data => { this.accUnits = data; console.log(data); });
   })
 
  console.log(this.searchForm);
  }

  onSorted(event) {
    this.spinner.show();
 
    setTimeout(() => {
        this.spinner.hide();
    }, 1000);
    this.accUnits = event;
  }

  onFilter(event) {
   // this.searchTerm=new AdditionalService();
    this.spinner.show();
 
    setTimeout(() => {
        this.spinner.hide();
    }, 1000);
    if(event != 'un') {
      this.addService.getAdditionalByName(event).subscribe(data => {
        this.searchTerm = data;
      })
    }

  }

}
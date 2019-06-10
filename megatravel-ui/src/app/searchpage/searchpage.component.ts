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
import { AccommodationunitService } from '../services/accommodationunit.service';
import { FilterObject } from '../models/FilterObject';

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
  event = null;
  listToFilter: FilterObject[] = [];
  globa;
  newFilter: FilterObject ;
  constructor(private route: ActivatedRoute, private reservationService: ReservationService, private pipe: DatePipe,
    private spinner: NgxSpinnerService, private addService: AdditionalservicesService,
    private accService: AccommodationunitService) { }

  ngOnInit() {

    this.route.params.forEach(a => {
      this.searchForm.checkin = this.pipe.transform(a.in, "yyyy-MM-dd");
      this.searchForm.checkout = this.pipe.transform(a.out, "yyyy-MM-dd");
      this.searchForm.destination = a.where;
      this.searchForm.persons = +a.persons;
      this.reservationService.getFreeAccUnits(this.searchForm).subscribe(data => { 
        this.globa = data;
        this.accUnits = data;       
        this.accUnits.forEach(ac => {
          this.accService.getRatingScore(ac.id).subscribe(data => {
            ac.rating = data;
          })
        })
        console.log(this.accUnits);
      });
   })
 
  }

  onSorted(event) {
    this.spinner.show();
 
    setTimeout(() => {
        this.spinner.hide();
    }, 1000);
    this.accUnits = event;
  }

  onFilter(event) {
    this.spinner.show();
 
    setTimeout(() => {
        this.spinner.hide();
    }, 1000);

    this.newFilter = new FilterObject();
    this.newFilter.name = event.target.name;
    this.newFilter.include = event.srcElement.checked;
    if(this.newFilter.include) {
      this.listToFilter.push(this.newFilter);
    }
    this.listToFilter.forEach(f => {     
        if(this.newFilter.include != true && f.name == this.newFilter.name) {
            this.listToFilter.splice(this.listToFilter.indexOf(f), 1);
        }
      }
    );
  }
}
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { SearchForm } from '../models/SearchForm';
import { ReservationService } from 'src/app/services/reservation.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { DatePipe } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {

  searchForm : SearchForm = new SearchForm();
  numbers = [1,2,3,4,5,6,7,8,9,10];
  accUnits : AccommodationUnit[] = [];
  apiUrl = "https://api.opencagedata.com/geocode/v1/json?q=";
  apiKey=  "&key=55f3c34bb9a3424d96a72154deca11ea&no_annotations=1&language=en";
  
  constructor(private route: ActivatedRoute, private reservationService: ReservationService, private pipe: DatePipe,
    private spinner: NgxSpinnerService, private http: HttpClient) { }

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

   this.getLatLong();
  }

  getLatLong() {
    let headers = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*" });
  let options = { headers: headers };
    let url = this.searchForm.destination.replace(/ /g, '%20');
    this.http.get(this.apiUrl+url+this.apiKey, options).subscribe(data => {
      alert(data.results.geometry.lat + ' ' + data.results.geometry.lng);
    })
  }

  onSorted(event) {
    this.spinner.show();
 
    setTimeout(() => {
        this.spinner.hide();
    }, 1000);
    this.accUnits = event;
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
import { DestinationObject } from '../models/DestinationObject';
import { Address } from '../models/Address';
import { DistanceFilter } from '../models/DistanceFilter';
import { DestinationFilter } from 'src/app/searchpage/destinationfilter.pipe';
import { DestinationSorter } from '../models/DestinationSorter';
import * as _ from 'underscore';
import { AuthService } from '../auth/service/auth.service';

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
  //listaAdresa: Address[] = [];
  something = null;
  listToFilter: FilterObject[] = [];
  destination:DestinationObject = new DestinationObject();
  newFilter: FilterObject ;
  difference;
  logged;
  distancesCalculated:DestinationSorter[] = [];
  distArray: DestinationSorter[] = [];
  constructor(private route: ActivatedRoute, private reservationService: ReservationService, private pipe: DatePipe,
    private filterPipe: DestinationFilter,
    private spinner: NgxSpinnerService, private addService: AdditionalservicesService,
    private accService: AccommodationunitService, private router: Router,
    private authService: AuthService) { }

  ngOnInit() {

    this.route.params.forEach(a => {
      this.searchForm.checkin = this.pipe.transform(a.in, "yyyy-MM-dd");
      this.searchForm.checkout = this.pipe.transform(a.out, "yyyy-MM-dd");
      this.searchForm.destination = a.where;
      this.searchForm.persons = +a.persons;
      this.reservationService.getFreeAccUnits(this.searchForm).subscribe(data => { 
        console.log(data);
        this.accUnits = data;
        this.accUnits.forEach(ac => {
          this.accService.getRatingScore(ac.id).subscribe(data => {
            ac.rating = data.toFixed(1);
          });
          // this.accService.getAddress(ac.accommodationObject.address).subscribe(data=>{
          //   this.listaAdresa.push(data);
          
          // });
        })
      });
      this.destination.distanceO = null;
      //this.reservationService.getAdress(this.accUnit.accommodationObject.addressId).subscribe(data => { this.address = data;});
      var dateone:any = new Date(this.searchForm.checkin);
      var datetwo:any = new Date(this.searchForm.checkout);
      this.difference = (datetwo - dateone) / 1000 / 60 / 60 / 24; 
      this.getLoggedUser();
   })  
 
  }

  makeAReservation(accUnit) {
    // let res : ReservationDTO = new ReservationDTO;
    // res.accommodationUnitId = this.accUnit.id;
    // res.beginDate = this.searchForm.checkin;
    // res.endDate = this.searchForm.checkout;
    // res.price = this.accUnit.price.price;
    // res.userId = this.logged.id;

    // console.log(res);
    if(this.logged == null){
      alert("Please login first!");
      this.router.navigate(['login']);
    }else{
      this.router.navigate(['/book', {in: this.searchForm.checkin, out: this.searchForm.checkout, user: this.logged.id, acu: accUnit.id, price: accUnit.price.price}]);
    }
  }

  ifForDelete(event){
    console.log("AJDEE" + event)
   // this.accUnits.splice(event, 1);
  }

  getLoggedUser() {
    this.authService.getLogged().subscribe(data => {
      this.logged = data;
    }, error => {
      this.logged = null;
    })
  }


  onSorted(event) {
    
    this.spinner.show();
 
    setTimeout(() => {
        this.spinner.hide();
    }, 1000);
    console.log(event);
  
    for(let v = 0; v < event.length; v++) {
      this.accUnits[v] = event[v];
    }
 

  }

  onRating(event) {
    if(event) {
      this.accUnits = _.sortBy(this.accUnits, 'rating');
      console.log(this.accUnits);      
    } else {
      this.accUnits = (_.sortBy(this.accUnits, 'rating')).reverse();
      console.log(this.accUnits);
    }
  }

  onPrice(event) {
    if(event) {
      this.accUnits = _.sortBy(this.accUnits, 'price.price');
      console.log(this.accUnits);
    } else {
      this.accUnits = (_.sortBy(this.accUnits, 'price.price')).reverse();
      console.log(this.accUnits);
      
    }
  }

  onCategory(event) {
    if(event) {
      this.accUnits = _.sortBy(this.accUnits, 'accommodationObject.categoryId');
      console.log(this.accUnits);      
    } else {
      this.accUnits = (_.sortBy(this.accUnits, 'accommodationObject.categoryId')).reverse();
      console.log(this.accUnits);
    }
  }

  onFilter(event) {
    this.spinner.show();
 
    setTimeout(() => {
        this.spinner.hide();
    }, 1000);

    var newFilter = new FilterObject();
    newFilter.name = event.target.name;
    newFilter.include = event.srcElement.checked;
    if(newFilter.include) {

      this.listToFilter.push(newFilter);
    }
    this.listToFilter.forEach(f => {     
        if(newFilter.include != true && newFilter.name == f.name) {
            this.listToFilter.splice(this.listToFilter.indexOf(f), 1);
        }
      }
    );
   
  }  

  onDistance(event) {
    this.spinner.show();
 
    setTimeout(() => {
        this.spinner.hide();
    }, 1000);

    if(event.unit != "" && event.distance != null) {
      let streetName = this.searchForm.destination.replace(/ /g,'+');
      this.accService.getDestinationInfo(streetName).subscribe(info => {
        this.destination.longitude = info[0].lon;
        this.destination.latitude = info[0].lat;
      });
      this.destination.distanceO = new DistanceFilter();
      this.destination.distanceO.distance = event.distance;
      this.destination.distanceO.unit = event.unit;

      //this.distancesCalculated = this.filterPipe.returnDistances();
      
      this.distancesCalculated = this.filterPipe.tempArray;
      
      

      
    }    
   
  }

}
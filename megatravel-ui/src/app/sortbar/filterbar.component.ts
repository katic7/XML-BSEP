import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { ifStmt } from '@angular/compiler/src/output/output_ast';
import { NgxSpinnerService } from 'ngx-spinner';
import { DestinationSorter } from '../models/DestinationSorter';
import { AccommodationunitService } from 'src/app/services/accommodationunit.service';
import { DestinationFilter } from 'src/app/searchpage/destinationfilter.pipe';
import { DestinationObject } from 'src/app/models/DestinationObject';
import { SearchForm } from 'src/app/models/SearchForm';
import { DistanceFilter } from 'src/app/models/DistanceFilter';
import * as _ from 'underscore';


@Component({
  selector: 'app-filterbar',
  templateUrl: './filterbar.component.html',
  styleUrls: ['./filterbar.component.css']
})
export class FilterbarComponent implements OnInit {
  lowestPriceBoolean:boolean = true;
  lowestRatingBoolean:boolean = true;
  lowestCategoryBoolean:boolean = true;
  //@Input() distancesCalculated:DestinationSorter[];
  @Input() accUnits:AccommodationUnit[];
  @Input() searchForm: SearchForm;
  distancesCalculated : DestinationSorter[] = [];
  @Output() sorted = new EventEmitter<AccommodationUnit[]>();
  lowestDistanceBoolean: boolean = true;
  result : AccommodationUnit[] = [];
  destination : DestinationObject = new DestinationObject();
  @Output() ascdesc = new EventEmitter<boolean>();
  @Output() catascdesc = new EventEmitter<boolean>();
  @Output() priceascdesc = new EventEmitter<boolean>();
  constructor(private accService: AccommodationunitService, private filterPipe: DestinationFilter) { }

  ngOnInit() {
    let streetName = this.searchForm.destination.replace(/ /g,'+');
      this.accService.getDestinationInfo(streetName).subscribe(info => {
        this.destination.longitude = info[0].lon;
        this.destination.latitude = info[0].lat;
        this.accUnits.forEach( u => {
          this.distancesCalculated = [];
          var ds = new DestinationSorter();
          ds.unitId = u.id;
          // this.accService.getAddress(u.accommodationObject.addressId).subscribe( data => { 
          //   ds.distanceInkm = this.filterPipe.calcCrow( this.destination.latitude, this.destination.longitude, data.latitude, data.longitude);
          //   this.distancesCalculated.push(ds); } )
          console.log(ds);       
        } )
      });      
  }

  priceLowest() {

    if(this.lowestPriceBoolean) {
      this.lowestPriceBoolean = false;
      this.priceascdesc.emit(this.lowestPriceBoolean);
    } else {
      this.lowestPriceBoolean = true;
      this.priceascdesc.emit(this.lowestPriceBoolean);
    }
  }

  review() {
    if(this.lowestRatingBoolean) {
      this.lowestRatingBoolean = false;
      this.ascdesc.emit(this.lowestRatingBoolean);
    } else {
      this.lowestRatingBoolean = true;
      this.ascdesc.emit(this.lowestRatingBoolean);
    }
  }

  category() {
    if(this.lowestCategoryBoolean) {
      this.lowestCategoryBoolean = false;
      this.catascdesc.emit(this.lowestCategoryBoolean);
    } else {
      this.lowestCategoryBoolean = true;
      this.catascdesc.emit(this.lowestCategoryBoolean);
    }
  }

  distance() {
    if(this.lowestDistanceBoolean) {
      this.lowestDistanceBoolean = false;
      this.distanceHighest();
    } else {
      this.lowestDistanceBoolean = true;
      this.distanceLowest();
    }
  }

  distanceLowest() {
    console.log(this.accUnits);
    console.log(this.distancesCalculated);
    this.distancesCalculated.sort((a, b) => {
      if (a.distanceInkm < b.distanceInkm ) {
          return -1;
      } else if (a.distanceInkm  > b.distanceInkm ) {
          return 1;
      } else {
          return 0;
      }
    })



    //this.distancesCalculated = _.sortBy(this.distancesCalculated, 'distanceInkm');

    this.result = [];

    this.distancesCalculated.forEach( d => {
      this.accUnits.forEach( a => {
        if(d.unitId == a.id) {
          this.result.push(a);
          return false;
        }
      } )
  })

  this.accUnits = this.result;
  console.log(this.distancesCalculated);
  console.log(this.result);
  console.log(this.accUnits);
  this.sorted.emit(this.accUnits);
  }
    
  distanceHighest() {
    this.distancesCalculated.sort((a, b) => {
      if (a.distanceInkm > b.distanceInkm ) {
          return -1;
      } else if (a.distanceInkm  < b.distanceInkm ) {
          return 1;
      } else {
          return 0;
      }
    })
    this.result = [];

    this.distancesCalculated.forEach( d => {
      this.accUnits.forEach( a => {
        if(d.unitId == a.id) {
          this.result.push(a);
          return false;
        }
      } )
  })

  this.accUnits = this.result;
  console.log(this.distancesCalculated);
  console.log(this.result);
  console.log(this.accUnits);
  this.sorted.emit(this.accUnits);
  }
 
}
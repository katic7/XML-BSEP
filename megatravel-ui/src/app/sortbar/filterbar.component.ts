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
         this.accService.getAddress(u.accommodationObject.address.id).subscribe( data => { 
           ds.distanceInkm = this.filterPipe.calcCrow( this.destination.latitude, this.destination.longitude, data.latitude, data.longitude);
            this.distancesCalculated.push(ds);
          
          } )
          console.log(this.distancesCalculated);       
        } )
      }); 
      
      for(var i = 0; i < this.accUnits.length; i++) {
        if(this.distancesCalculated[i].unitId == this.accUnits[i].id) {
          this.accUnits[i].distance = this.distancesCalculated[i].distanceInkm;
        }
      }
  }

  priceLowest() {

    this.accUnits = _.sortBy(this.accUnits, 'price.price');
    if(this.lowestPriceBoolean) {
      this.lowestPriceBoolean = false;
      this.sorted.emit(this.accUnits);
    } else {
      this.lowestPriceBoolean = true;
      this.sorted.emit(this.accUnits.reverse());
    }

  }

  review() {

    this.accUnits = _.sortBy(this.accUnits, 'rating');
    if(this.lowestRatingBoolean) {
      this.lowestRatingBoolean = false;
      this.sorted.emit(this.accUnits);
    } else {
      this.lowestRatingBoolean = true;
      this.sorted.emit(this.accUnits.reverse());
    }
  }

  category() {
    this.accUnits = _.sortBy(this.accUnits, 'accommodationObject.category.name');
    if(this.lowestCategoryBoolean) {
      this.lowestCategoryBoolean = false;
      this.sorted.emit(this.accUnits.reverse());
    } else {
      this.lowestCategoryBoolean = true;
      this.sorted.emit(this.accUnits.reverse());
    }

  }

  distance() {
    this.accUnits  = _.sortBy(this.accUnits, 'distance')
    if(this.lowestDistanceBoolean) {
      this.lowestDistanceBoolean = false;
      this.sorted.emit(this.accUnits.reverse());
    
    } else {
      this.lowestDistanceBoolean = true;
      this.sorted.emit(this.accUnits);

    }
  }

  
}
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { ifStmt } from '@angular/compiler/src/output/output_ast';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-filterbar',
  templateUrl: './filterbar.component.html',
  styleUrls: ['./filterbar.component.css']
})
export class FilterbarComponent implements OnInit {
  lowestPriceBoolean:boolean = true;
  lowestRatingBoolean:boolean = true;
  lowestCategoryBoolean:boolean = true;
  @Input() accUnits:AccommodationUnit[];
  @Output() sorted = new EventEmitter<AccommodationUnit[]>();

  constructor() { }

  ngOnInit() {
    
  }

  priceLowest() {

    if(this.lowestPriceBoolean) {
      this.lowestPriceBoolean = false;
      this.sortedByPriceHighests();
      this.sorted.emit(this.accUnits);
    } else {
      this.lowestPriceBoolean = true;
      this.sortedByPriceLowest();
      this.sorted.emit(this.accUnits);
    }
  }

  review() {
    if(this.lowestRatingBoolean) {
      this.lowestRatingBoolean = false;
      this.sortedByReviewHighest();
      this.sorted.emit(this.accUnits);
    } else {
      this.lowestRatingBoolean = true;
      this.sortedByReviewLowest();
      this.sorted.emit(this.accUnits);
    }
  }

  category() {
    if(this.lowestCategoryBoolean) {
      this.lowestCategoryBoolean = false;
      this.sortedByCategoryHighest();
      this.sorted.emit(this.accUnits);
    } else {
      this.lowestCategoryBoolean = true;
      this.sortedByCategoryLowest();
      this.sorted.emit(this.accUnits);
    }
  }

  sortedByReviewLowest() {
    this.accUnits.sort((a, b) => {
      if (a.rating < b.rating ) {
          return -1;
      } else if (a.rating  > b.rating ) {
          return 1;
      } else {
          return 0;
      }
    })
  }

  sortedByReviewHighest() {
    this.accUnits.sort((a, b) => {
      if (a.rating > b.rating ) {
          return -1;
      } else if (a.rating  < b.rating ) {
          return 1;
      } else {
          return 0;
      }
    })
  }

  sortedByPriceLowest() {
    this.accUnits.sort((a, b) => {
      if (a.price.price < b.price.price ) {
          return -1;
      } else if (a.price.price  > b.price.price ) {
          return 1;
      } else {
          return 0;
      }
    })
  }

  sortedByPriceHighests() {
    this.accUnits.sort((a, b) => {
      if (a.price.price > b.price.price ) {
          return -1;
      } else if (a.price.price  < b.price.price ) {
          return 1;
      } else {
          return 0;
      }
    })
  }

  sortedByCategoryHighest() {
    this.accUnits.sort((a, b) => {
      if (a.accommodationObject.categoryId < b.accommodationObject.categoryId ) {
          return -1;
      } else if (a.accommodationObject.categoryId > b.accommodationObject.categoryId ) {
          return 1;
      } else {
          return 0;
      }
    })
  }

  sortedByCategoryLowest() {
    this.accUnits.sort((a, b) => {
      if (a.accommodationObject.categoryId > b.accommodationObject.categoryId ) {
          return -1;
      } else if (a.accommodationObject.categoryId  < b.accommodationObject.categoryId ) {
          return 1;
      } else {
          return 0;
      }
    })
  }
    
 
}
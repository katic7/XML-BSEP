import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { ifStmt } from '@angular/compiler/src/output/output_ast';
import { NgxSpinnerService } from 'ngx-spinner';
import { AdditionalService } from '../models/AdditionalService';
import { AdditionalservicesService } from '../services/additionalservices.service';
import { FormControl } from '@angular/forms';
import { DistanceFilter } from '../models/DistanceFilter';
import { AccommodationunitService } from '../services/accommodationunit.service';
import { Type } from '../models/Type';
import { Category } from '../models/Category';

@Component({
  selector: 'app-filtersection',
  templateUrl: './filtersection.component.html',
  styleUrls: ['./filtersection.component.css']
})
export class FiltersectionComponent implements OnInit {
  @Input() accUnits:AccommodationUnit[];
  @Output() sorted = new EventEmitter<String>();
  allAdditionalServices : AdditionalService[] = [];
  allTypes : Type[] = [];
  allCategories : Category[] = [];
  distance = new FormControl("");
  unit = new FormControl("");
  @Output() ret = new EventEmitter<DistanceFilter>();

  constructor(private additionalServices: AdditionalservicesService, private accommodationUnitService : AccommodationunitService) { }

  ngOnInit() {
    this.additionalServices.getAllAdditionalServices().subscribe(data => {
      this.allAdditionalServices = data;
    });
    this.accommodationUnitService.getAllTypes().subscribe(dataTypes =>{
      this.allTypes = dataTypes;
    });
    this.accommodationUnitService.getAllCategories().subscribe(dataCateg =>{
      this.allCategories = dataCateg;
    });

  }

  filter(event) {
    this.sorted.emit(event); 
  }

  onInput() {
    let ret = new DistanceFilter();
    if(this.distance.value == ""){
      ret.distance = 9999999999;
    }else {
      ret.distance = this.distance.value;
    }
    ret.unit = this.unit.value;
    this.ret.emit(ret);
  }

}

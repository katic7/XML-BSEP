import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { ifStmt } from '@angular/compiler/src/output/output_ast';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-filtersection',
  templateUrl: './filtersection.component.html',
  styleUrls: ['./filtersection.component.css']
})
export class FiltersectionComponent implements OnInit {
  @Input() accUnits:AccommodationUnit[];
  @Output() sorted = new EventEmitter<String>();

  constructor() { }

  ngOnInit() {

  }

  filter(event) {
    this.sorted.emit(event.srcElement.name);
  }

}

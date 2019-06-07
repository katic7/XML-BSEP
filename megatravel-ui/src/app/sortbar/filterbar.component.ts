import { Component, OnInit, Input } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';

@Component({
  selector: 'app-filterbar',
  templateUrl: './filterbar.component.html',
  styleUrls: ['./filterbar.component.css']
})
export class FilterbarComponent implements OnInit {

  @Input() accUnits:AccommodationUnit[];

  constructor() { }

  ngOnInit() {
  }


  activeButton
  showPhase(event){
    this.activeButton = event;
    switch(event) {
      case 1: this.sortBy("price");
    }
    this.accUnits[0].description = "aaa";
  }

 sortBy(field: string) {
    alert(field);
        this.accUnits.sort((a: any, b: any) => {
            if (a[field] < b[field]) {
                return -1;
            } else if (a[field] > b[field]) {
                return 1;
            } else {
                return 0;
            }
        });
        this.accUnits = this.accUnits;
}

}

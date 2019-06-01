import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Price } from 'src/app/models/Price';
import { AdditionalService } from 'src/app/models/AdditionalService';
import { Router } from '@angular/router';
import { PriceService } from 'src/app/services/price.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { Reservations } from 'src/app/models/Reservations';
import { AccomoodationUnitService } from 'src/app/services/accomoodation-unit.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {

  constructor(private router: Router, private priceService : PriceService, private accUnitService : AccomoodationUnitService, private datePipe: DatePipe) { }

  show_register_form : boolean = false;
  show_newPrice : boolean = false;
  form: any = {};
  numberOfBeds = new FormControl('');
  balcony = new FormControl('');
  description = new FormControl('');
  priceFORM = new FormControl('');
  startDate = new FormControl('');
  endDate = new FormControl('');
  newPriceValue = new FormControl('');
  additionalServices = new FormControl('');
  addedPrice : Price = new Price;
  accommodationUnit : AccommodationUnit = new AccommodationUnit();
  reservations : Array<Reservations> = new Array<Reservations>();
  //-----------------TESTING---------------------------
  prices : Array<Price> = new Array<Price>();
  price1 : Price; price2 : Price;
  additionalServicesARRAY : Array<AdditionalService>;
  //--------------------------------------------

  addAccUnit(){
    this.show_register_form = true;
    //-----------------TESTING---------------------------
    this.price1 = new Price;
    this.price1.id = 1;
    this.price1.startDate = "1/1/2019";
    this.price1.endDate = "1/2/2019";
    this.price1.price = 200;
    this.prices.push(this.price1);
    this.price2 = new Price;
    this.price2.id = 1;
    this.price2.startDate = "1/1/2019";
    this.price2.endDate = "1/2/2019";
    this.price2.price = 500;
    this.prices.push(this.price2);
    this.additionalServicesARRAY = Array(
      { "id": 0, "name": "Wifi", "price": 10, "included": true},
      { "id": 1, "name": "Pool", "price": 30, "included": true},
      { "id": 2, "name": "Breakfast", "price": 80, "included": true}
    );
    //-------------------------------------------
  }

  Cancel(){
    this.router.navigate(['/home']);  
  }

  showPriceFrom(){
    this.show_newPrice = true;
  }

  addPrice(){
    this.show_newPrice = false;
    this.addedPrice.startDate = this.datePipe.transform(this.startDate.value,'yyyy-MM-dd');
    this.addedPrice.endDate = this.datePipe.transform(this.endDate.value,'yyyy-MM-dd');
    this.addedPrice.price = this.newPriceValue.value;
    this.prices.push(this.addedPrice);
    /*
    this.priceService.addNewPrice(addedPrice).subscribe(data=>{

    });
    */
    var clearFC = new FormControl('');
    this.startDate = clearFC;
    this.endDate = clearFC;
    this.newPriceValue = clearFC;
  }

  CancelPrice(){
    this.show_newPrice = false;
    var clearFC = new FormControl('');
    this.startDate = clearFC;
    this.endDate = clearFC;
    this.newPriceValue = clearFC;
  }

  onSubmit(){
     var newAccommodationUnit : AccommodationUnit = new AccommodationUnit();
     newAccommodationUnit.numberOfBeds = this.numberOfBeds.value;
     newAccommodationUnit.description = this.description.value;
     newAccommodationUnit.price = this.priceFORM.value;
     newAccommodationUnit.balcony = this.balcony.value;
     newAccommodationUnit.accObjectId = null; //??
     newAccommodationUnit.image = null;
     newAccommodationUnit.reservations = this.reservations;     
     newAccommodationUnit.rating = 0;
     newAccommodationUnit.additionalServices = this.accommodationUnit.additionalServices;
     console.table(newAccommodationUnit);
     /*
     this.accUnitService.addNewAccU(newAccommodationUnit).subscribe(data=>{

     });
     */
    alert("Success!!!");
    var clearFC = new FormControl('');
    this.numberOfBeds = clearFC;
    this.description = clearFC;
    this.priceFORM = clearFC;
    this.balcony = clearFC;
    this.accommodationUnit.additionalServices = null;

  }

  ngOnInit() {
    

  }

}

import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { AccUnitPrice } from 'src/app/models/AccUnitPrice';
import { AdditionalService } from 'src/app/models/AdditionalService';
import { Router } from '@angular/router';
import { PriceService } from 'src/app/services/price.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';
import { Reservations } from 'src/app/models/Reservations';
import { AccomoodationUnitService } from 'src/app/services/accomoodation-unit.service';
import { DatePipe } from '@angular/common';
import { AdditionalServiceService } from 'src/app/services/additional-service.service';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {

  constructor(private router: Router, private priceService : PriceService, private accUnitService : AccomoodationUnitService, private datePipe: DatePipe, private additionalServiceService : AdditionalServiceService) { }

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
  addedPrice : AccUnitPrice = new AccUnitPrice;
  accommodationUnit : AccommodationUnit = new AccommodationUnit();
  reservations : Array<Reservations> = new Array<Reservations>();
  additionalServicesARRAY : Array<AdditionalService>;
  
  prices : Array<AccUnitPrice> = new Array<AccUnitPrice>();
  price1 : AccUnitPrice; price2 : AccUnitPrice;
  

  addAccUnit(){
    this.show_register_form = true;
    this.additionalServiceService.getAllAdditionalServ().subscribe(data=>{
      this.additionalServicesARRAY = data;
    });
    this.price1 = new AccUnitPrice;
    this.price1.id = 1;
    this.price1.startDate = "1996-01-01";
    this.price1.endDate = "1996-01-01";
    this.price1.price = 200;
    this.prices.push(this.price1);
  }

  Cancel(){
    this.router.navigate(['/home']);  
  }

  showPriceFrom(){
    this.show_newPrice = true;
  }

  CancelPrice(){
    this.show_newPrice = false;

    this.startDate = new FormControl('');
    this.endDate = new FormControl('');
    this.newPriceValue = new FormControl('');
  }

  balconyChanged(){
    console.log(this.balcony.value);
  }

  onSubmit(){
     this.addedPrice.startDate = this.datePipe.transform(this.startDate.value,'yyyy-MM-dd');
     this.addedPrice.endDate = this.datePipe.transform(this.endDate.value,'yyyy-MM-dd');
     this.addedPrice.price = this.newPriceValue.value;
     var newAccommodationUnit : AccommodationUnit = new AccommodationUnit();
     newAccommodationUnit.numberOfBeds = this.numberOfBeds.value;
     newAccommodationUnit.balcony = false;
     if(this.balcony.value){
      newAccommodationUnit.balcony = true;
     }
     this.accommodationUnit.price = this.addedPrice;
     newAccommodationUnit.price = this.accommodationUnit.price;
     newAccommodationUnit.description = this.description.value;
     newAccommodationUnit.rating = 0;
     newAccommodationUnit.image = null;
     newAccommodationUnit.accObjectId = 2; //??
     newAccommodationUnit.reservations = this.reservations;     
     newAccommodationUnit.additionalServices = this.accommodationUnit.additionalServices;
     this.accUnitService.addNewAccU(newAccommodationUnit).subscribe(data=>{
        console.table(data);
     });
     
    this.numberOfBeds = new FormControl('');
    this.description =  new FormControl('');
    this.accommodationUnit.price = null;
    this.balcony =  new FormControl('');
    this.accommodationUnit.additionalServices = null;
    this.startDate = new FormControl('');
    this.endDate = new FormControl('');
    this.newPriceValue = new FormControl('');
    
  }

  ngOnInit() {
    

  }

}

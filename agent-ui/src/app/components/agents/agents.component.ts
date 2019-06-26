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
import { AuthService } from '../auth/service/auth.service';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {

  constructor(private authService : AuthService, private router: Router, private priceService : PriceService, private accUnitService : AccomoodationUnitService, private datePipe: DatePipe, private additionalServiceService : AdditionalServiceService) { }

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
  servisi : AdditionalService [] = [];
  logged : User;
  accId : number;

  addAccUnit(){
    this.show_register_form = true;
    this.additionalServiceService.getAllAdditionalServ().subscribe(data=>{
      this.additionalServicesARRAY = data;
    });
    
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
     newAccommodationUnit.accObjectId = this.accId; 
     newAccommodationUnit.reservations = this.reservations;     
     newAccommodationUnit.additionalServices = this.servisi;
     /*this.accUnitService.addNewAccU(newAccommodationUnit).subscribe(data=>{
        console.table(data);
     });*/
     /*this.priceService.addNewPrice(this.accommodationUnit.price).subscribe(data=>{
       newAccommodationUnit.price.id = data.id;
       this.accUnitService.addNewAccU(newAccommodationUnit).subscribe(acc=>{

       });
     });*/
     this.accUnitService.addNewAccU(newAccommodationUnit).subscribe(data=>{
       this.router.navigate(['home']);
     })
     
    this.numberOfBeds = new FormControl('');
    this.description =  new FormControl('');
    this.accommodationUnit.price = null;
    this.balcony =  new FormControl('');
    this.accommodationUnit.additionalServices = null;
    this.startDate = new FormControl('');
    this.endDate = new FormControl('');
    this.newPriceValue = new FormControl('');

    
  }
  onSelection(e, v) {
    //alert("usao")
    console.log(e.option.selected);
    if(e.option.selected == true){
      this.servisi.push(e.option.value);
    }else{
      const index :number = this.servisi.indexOf(e.option.value);
      if(index != -1){
        this.servisi.splice(index,1);
      } 
    }
  }

  ngOnInit() {
    this.additionalServiceService.getAllAdditionalServ().subscribe(data=>{
      this.additionalServicesARRAY = data;
    });
    this.authService.getLogged().subscribe(data=>{
      this.logged = data;
      console.log(data.roles[0].name + "AAAAAAAAAAAAA");
      if(data.roles[0].name == "ROLE_AGENT"){
        this.authService.getOneAgent(this.logged.id).subscribe(agnet=>{
          if(agnet.accObj == null){
            this.router.navigate(['newObject']);
            
          }else{
            this.accId = agnet.accObj.id;
          }
        })
      }else{
        this.authService.logout();
        alert("Nemate rolu agent!");
        this.router.navigate(['login']);
      }
    }, error=>{
      alert("Morate biti ulogovani!")
      this.router.navigate(['login']);
    });
  }
  

}

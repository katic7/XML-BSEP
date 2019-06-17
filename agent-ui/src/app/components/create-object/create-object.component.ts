import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/Address';
import { AccommodationObject } from 'src/app/models/AccommodationObject';
import { MatSelectModule } from '@angular/material';
import { FormControl } from '@angular/forms';
import { strictEqual } from 'assert';
import { stringify } from '@angular/core/src/util';
import { CreateObjectService } from './services/create-object.service';

@Component({
  selector: 'app-create-object',
  templateUrl: './create-object.component.html',
  styleUrls: ['./create-object.component.css']
})
export class CreateObjectComponent implements OnInit {

  address : Address = new Address();
  accObj : AccommodationObject = new AccommodationObject();
  kat : FormControl = new FormControl("");
  tip : FormControl = new FormControl("");
  adr : string;

  constructor(private createService : CreateObjectService) { }

  ngOnInit() {
  }

  onSubmit(){
    if(this.accObj.freeCancelation == undefined){
      this.accObj.freeCancelation = false;
    }
    this.accObj.typeId = this.tip.value;
    alert(this.accObj.typeId)
    alert(this.accObj.freeCancelation);
    alert
    this.adr = this.address.street.replace(" ", "+");
    this.adr = this.adr + '+' +this.address.streetNumber;
   /* this.createService.getLocation(this.adr).subscribe(data=>{
      this.address.longitude = data[0].lon;
      this.address.latitude = data[0].lat;
      this.createService.createAddress(this.address).subscribe(data=>{
        this.accObj.addressId = data.id;
      });
    });*/
  }
}

import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/Address';
import { AccommodationObject } from 'src/app/models/AccommodationObject';
import { MatSelectModule } from '@angular/material';
import { FormControl } from '@angular/forms';
import { strictEqual } from 'assert';
import { stringify } from '@angular/core/src/util';
import { CreateObjectService } from './services/create-object.service';
import { HttpRequest } from '@angular/common/http';
import { AuthService } from '../auth/service/auth.service';
import { User } from 'src/app/models/User';
import { Router } from '@angular/router';
import { Type } from 'src/app/models/Type';
import { Category } from 'src/app/models/Category';

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
  tipovi : Type[] = [];
  kategorije : Category[] = [];
  adr : string;
  logged : User;

  constructor(private createService : CreateObjectService, private authService: AuthService, private router : Router) { }

  ngOnInit() {

    this.authService.getLogged().subscribe(data=>{
      this.logged = data;
      this.createService.getAllCategories().subscribe(cat=>{
        this.kategorije = cat;
      });
      this.createService.getAllTypes().subscribe(tip=>{
        this.tipovi = tip;
      })
    },error=>{
      alert("Morate biti ulogovani!")
      this.router.navigate(['login']);
    });
  }

  onSubmit(){
    if(this.accObj.freeCacelation == undefined){
      this.accObj.freeCacelation = false;
    }
    this.accObj.typeId = this.tip.value;
    this.accObj.categoryId = this.kat.value;
    alert(this.accObj.freeCacelation);
    alert
    this.adr = this.address.street.replace(" ", "+");
    this.adr = this.adr + '+' +this.address.streetNumber;
    this.createService.getLocation(this.adr).subscribe(data=>{
      this.address.longitude = data[0].lon;
      this.address.latitude = data[0].lat;
      this.createService.createAddress(this.address).subscribe(data=>{
        this.accObj.addressId = data.id;
        this.createService.createObject(this.accObj).subscribe(acc=>{
          this.router.navigate(['home']);
        });
        
      });
    });  
  
  }
}

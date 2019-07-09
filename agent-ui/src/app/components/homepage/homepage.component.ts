import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/service/auth.service';
import { User } from 'src/app/models/User';
import { RoleName } from 'src/app/models/Role';
import { Router } from '@angular/router';
import { AccommodationObject } from 'src/app/models/AccommodationObject';
import { AddressServiceService } from 'src/app/services/address-service.service';
import { Address } from 'src/app/models/Address';
import { AccomoodationUnitService } from 'src/app/services/accomoodation-unit.service';
import { AccommodationUnit } from 'src/app/models/AccommodationUnit';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  logged : User;
  agentJe : boolean = true;
  accObj : AccommodationObject = new AccommodationObject();
  tip:string;
  adr : Address = new Address();
  freeCancel :string;
  constructor(private authService : AuthService, private router : Router,private addressService : AddressServiceService, private unitService : AccomoodationUnitService) { }
  allUnits : AccommodationUnit[] = [];
  imaUnita : boolean = false;

  ngOnInit() {
    this.authService.getLogged().subscribe(data=>{
      this.logged = data;
      console.log(data.roles[0].name + "AAAAAAAAAAAAA");
      if(data.roles[0].name == "ROLE_AGENT"){
        this.authService.getOneAgent(this.logged.id).subscribe(agnet=>{
          if(agnet.accObj == null){
            this.router.navigate(['newObject']);
            
          }else{
            this.accObj = agnet.accObj;
          if(agnet.accObj.freeCacelation == true){
            this.freeCancel = "Yes";
          }else{
            this.freeCancel = "No";
          }
          this.addressService.getAddress(agnet.accObj.addressId).subscribe(adresa=>{
            this.adr = adresa;
          })
          this.unitService.getObjectUnits(agnet.accObj.id).subscribe(units=>{
            this.allUnits = units;
            if(units.length > 0){
              this.imaUnita = true;
            }
          })
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

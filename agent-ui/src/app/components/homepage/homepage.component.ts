import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/service/auth.service';
import { User } from 'src/app/models/User';
import { RoleName } from 'src/app/models/Role';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  logged : User;
  agentJe : boolean = true;
  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit() {
    this.authService.getLogged().subscribe(data=>{
      this.logged = data;
      console.log(data.roles[0].name + "AAAAAAAAAAAAA");
      if(data.roles[0].name == "ROLE_AGENT"){
        this.authService.getOneAgent(this.logged.id).subscribe(agnet=>{
          if(agnet.accObj == null){
            this.router.navigate(['newObject']);
            
          }else{
            
          }
        })
      }else{
        this.authService.signOut();
        alert("Nemate rolu agent!");
        this.router.navigate(['login']);
      }
    }, error=>{
      alert("Morate biti ulogovani!")
      this.router.navigate(['login']);
    });
  }

}

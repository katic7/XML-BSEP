import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/service/auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { UserService } from '../services/user.service';
import { ActivateUser } from 'src/app/models/ActivateUser';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { FormControl } from '@angular/forms';
import { AccommodationObject } from 'src/app/models/AccommodationObject';
import { AccObjectService } from '../services/acc-object.service';
import { CreateAgent } from 'src/app/models/CreateAgent';
import { AgentService } from '../services/agent.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  constructor(private authService : AuthService, private router: Router, private userService : UserService, private modalService: NgbModal, private accObjectService : AccObjectService, private agentService : AgentService) { }

  users : Array<User> = new Array<User>();
  acU : ActivateUser;
  closeResult: string;
  accommodationObjects : AccommodationObject[] = [];
  taxNumber = new FormControl('');
  //accommodationObjectFC = new FormControl('');
  accommodationObject : AccommodationObject = new AccommodationObject();
  usr_id :number;
  agent_user : CreateAgent = new CreateAgent();

  ActivateUser(user){
    this.acU = new ActivateUser(user.id, true, "ACTIVATE");
    this.userService.setUserActive(this.acU).subscribe(data =>{
      user.enabled = data.enabled;
      console.table(data);
    });
  }

  BlockUser(user){
    this.acU = new ActivateUser(user.id, true, "BLOCK");
    this.userService.setUserActive(this.acU).subscribe(data =>{
      user.nonLocked = data.nonLocked;
      console.table(data);
    });
  }

  DeleteUser(user){
    this.userService.deleteUser(user.id).subscribe(data => {});
    const index: number = this.users.indexOf(user);
    if (index !== -1) {
        alert("usao");
        this.users.splice(index, 1);
    }
  }

  
  open(content,usrrr_id) {
    this.modalService.open(content, { centered: true });
    this.usr_id = usrrr_id;
  }

  AddNewAgent(){
    this.modalService.dismissAll();
    this.agent_user.user = this.usr_id;
    this.agent_user.pib = this.taxNumber.value;
    this.agent_user.accObj = this.accommodationObject.id;
    console.log( this.agent_user);
    this.agentService.addAgent(this.agent_user).subscribe(data=>{
      console.log(data);
    });
    window.location.reload();
    
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  ngOnInit() {
    this.userService.getAllUsers().subscribe(data =>{
      this.users = data;  
    });
    this.accObjectService.getAccObjects().subscribe(data=>{
      this.accommodationObjects = data;
    })
  }

 

}

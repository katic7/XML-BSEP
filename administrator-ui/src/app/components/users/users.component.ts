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
import { Agent } from 'src/app/models/Agent';

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
  accommodationObject : AccommodationObject = new AccommodationObject();
  usr_id :number;
  agent_user : CreateAgent = new CreateAgent();
  agents : Agent[] = [];
  logged: User;

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
        this.users.splice(index, 1);
    }
  }

  DeleteAgent(user){
    this.userService.deleteUser(user.id).subscribe(data => {});
    const index: number = this.agents.indexOf(user);
    if (index !== -1) {
        this.agents.splice(index, 1);
    }
  }

  open(content, usrrr_id) {
    this.usr_id = usrrr_id;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): void {
    if (reason === "Save click") {
      //add agent 
      this.agent_user.user = this.usr_id;
      this.agent_user.pib = this.taxNumber.value;
      this.agent_user.accObj = this.accommodationObject.id;
      console.log( this.agent_user);
      this.agentService.addAgent(this.agent_user).subscribe(data=>{
          const index: number = this.users.findIndex(user =>user.id === this.usr_id);
            if (index !== -1) {
              this.users.splice(index, 1);
            }
        this.agentService.getOneAgent(this.usr_id).subscribe(ag=>{
          this.agents.push(ag);
        });
      });
    }
  }

  ngOnInit() {
    this.userService.getOnlyUsers().subscribe(data =>{
      this.users = data;  
    });
    this.accObjectService.getAccObjects().subscribe(data=>{
      this.accommodationObjects = data;
    });
    this.agentService.getAll().subscribe(data=>{
      this.agents = data;
    });
    this.authService.getLogged().subscribe(data => {
      this.logged = data;
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/service/auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { UserService } from '../services/user.service';
import { ActivateUser } from 'src/app/models/ActivateUser';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  constructor(private authService : AuthService, private router: Router, private userService : UserService) { }

  users : Array<User> = new Array<User>();
  acU : ActivateUser;

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

  ngOnInit() {
    this.userService.getAllUsers().subscribe(data =>{
      this.users = data;  
    });
  }

 

}

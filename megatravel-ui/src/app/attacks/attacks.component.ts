import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-attacks',
  templateUrl: './attacks.component.html',
  styleUrls: ['./attacks.component.css']
})
export class AttacksComponent implements OnInit {

  constructor(private route:Router,private location: Location) { }

  hotel = new FormControl();
  pressed : boolean = false;

  ngOnInit() {
  }

  xssAttack(){
    this.location.replaceState("/search="+this.hotel.value);
    this.pressed=true;
  }

}

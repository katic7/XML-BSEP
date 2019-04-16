import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TestRating } from '../models/TestRating';

@Component({
  selector: 'app-attacks',
  templateUrl: './attacks.component.html',
  styleUrls: ['./attacks.component.css']
})
export class AttacksComponent implements OnInit {

  constructor(private route:Router,private location: Location, private httpClient : HttpClient) { }

  hotel = new FormControl();
  stars = new FormControl();
  pressed : boolean = false;
  selectedFile :File = null;
  ngOnInit() {
  }

  xssAttack(){
    this.location.replaceState("/search="+this.hotel.value);
    this.pressed=true;
  }

  zeroStarAttack(){
    if(this.stars.value == null){
      alert("Please add rating");
    }else{
      const testRating = new TestRating();
      testRating.stars = this.stars.value;
      this.httpClient.post("http://localhost:8081/accommodation-service/api/addresses/testRating", testRating).subscribe(data=>{
        console.log(data);
      });
    }
  } 

  onFileSelected(event){
    console.log(event);
    this.selectedFile = event.target.files[0];
  }

  onUpload(){
    const fd = new FormData();
    fd.append("image",this.selectedFile, this.selectedFile.name);
  }

}

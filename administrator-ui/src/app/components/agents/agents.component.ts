import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {

  constructor() { }

  show_register_form : boolean = false;
  form: any = {};
  name = new FormControl('');
  surname = new FormControl('');
  address = new FormControl('');
  taxNumber = new FormControl('');

  addAgent(){
    this.show_register_form = true;
  }

  onSubmit(){
    //popunjavanje podataka iz formcontrola i slanje novog agenta preko servisa..  
  }

  ngOnInit() {
  }

}

import { Component, OnInit } from '@angular/core';
import { Type } from 'src/app/models/Type';
import { Category } from 'src/app/models/Category';
import { AdditionalService } from 'src/app/models/AdditionalService';
import { AccObjectService } from 'src/app/components/services/acc-object.service';
import { FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-codebook',
  templateUrl: './codebook.component.html',
  styleUrls: ['./codebook.component.css']
})
export class CodebookComponent implements OnInit {

  types : Type[] = [];
  categories : Category[] = [];
  additionalServices : AdditionalService[] = [];
  closeResult: string;

  newName = new FormControl('');




  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): void {
    if (reason === "Save click") {
      //add agent 
      alert("BRAO");
    }
  }



  constructor(private accService: AccObjectService, private modalService: NgbModal) { }

  ngOnInit() {
    this.accService.getAllTypes().subscribe(data => { this.types = data; });
    this.accService.getAllCategories().subscribe(data => { this.categories = data; });
    this.accService.getAllAdditionalSevrices().subscribe(data => { this.additionalServices = data; });
  }

}

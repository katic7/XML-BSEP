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

  showAddType : boolean = false;
  showAddCategory : boolean = false;
  showAddAdditionalService : boolean = false;
  showEditType : boolean = false;
  showEditCategory : boolean = false;
  showEditAdditionalService : boolean = false;
  showDeleteType : boolean = false;
  showDeleteCategory : boolean = false;
  showDeleteAdditionalService : boolean = false;

  editingId : number = 0;

  newName = new FormControl('');
  newPrice = new FormControl('');




  open(content, broj, id, value1, value2) {

    switch(broj) {
      case 1 : {
        this.showAddType = true;
        this.showAddCategory = false;
        this.showAddAdditionalService = false;
        this.showEditType = false;
        this.showEditCategory = false;
        this.showEditAdditionalService = false;
        this.showDeleteType = false;
        this.showDeleteCategory = false;
        this.showDeleteAdditionalService = false;
        this.newName.setValue("");
        break;
      }
      case 2 : {
        this.showAddType = false;
        this.showAddCategory = true;
        this.showAddAdditionalService = false;
        this.showEditType = false;
        this.showEditCategory = false;
        this.showEditAdditionalService = false;
        this.showDeleteType = false;
        this.showDeleteCategory = false;
        this.showDeleteAdditionalService = false;
        this.newName.setValue("");
        break;
      }
      case 3 : {
        this.showAddType = false;
        this.showAddCategory = false;
        this.showAddAdditionalService = true;
        this.showEditType = false;
        this.showEditCategory = false;
        this.showEditAdditionalService = false;
        this.showDeleteType = false;
        this.showDeleteCategory = false;
        this.showDeleteAdditionalService = false;
        this.newName.setValue("");
        this.newPrice.setValue("");
        break;
      }
      case 4 : {
        this.showAddType = true;
        this.showAddCategory = false;
        this.showAddAdditionalService = false;
        this.showEditType = true;
        this.showEditCategory = false;
        this.showEditAdditionalService = false;
        this.showDeleteType = false;
        this.showDeleteCategory = false;
        this.showDeleteAdditionalService = false;
        this.newName.setValue(value1);
        this.editingId = id;
        break;
      }
      case 5 : {
        this.showAddType = false;
        this.showAddCategory = true;
        this.showAddAdditionalService = false;
        this.showEditType = false;
        this.showEditCategory = true;
        this.showEditAdditionalService = false;
        this.showDeleteType = false;
        this.showDeleteCategory = false;
        this.showDeleteAdditionalService = false;
        this.newName.setValue(value1);
        this.editingId = id;
        break;
      }
      case 6 : {
        this.showAddType = false;
        this.showAddCategory = false;
        this.showAddAdditionalService = true;
        this.showEditType = false;
        this.showEditCategory = false;
        this.showEditAdditionalService = true;
        this.showDeleteType = false;
        this.showDeleteCategory = false;
        this.showDeleteAdditionalService = false;
        this.newName.setValue(value1);
        this.newPrice.setValue(value2);
        this.editingId = id;
        break;
      }
      case 7 : {
        this.showAddType = false;
        this.showAddCategory = false;
        this.showAddAdditionalService = false;
        this.showEditType = false;
        this.showEditCategory = false;
        this.showEditAdditionalService = false;
        this.showDeleteType = true;
        this.showDeleteCategory = false;
        this.showDeleteAdditionalService = false;
        this.editingId = id;
        break;
      }
      case 8 : {
        this.showAddType = false;
        this.showAddCategory = false;
        this.showAddAdditionalService = false;
        this.showEditType = false;
        this.showEditCategory = false;
        this.showEditAdditionalService = false;
        this.showDeleteType = false;
        this.showDeleteCategory = true;
        this.showDeleteAdditionalService = false;
        this.editingId = id;
        break;
      }
      case 9 : {
        this.showAddType = false;
        this.showAddCategory = false;
        this.showAddAdditionalService = false;
        this.showEditType = false;
        this.showEditCategory = false;
        this.showEditAdditionalService = false;
        this.showDeleteType = false;
        this.showDeleteCategory = false;
        this.showDeleteAdditionalService = true;
        this.editingId = id;
        break;
      }
    }


    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  

  private getDismissReason(reason: any): void {
    /*if (reason === "Add type") {
      //add agent 
      alert("TYPE");
    }*/
    switch(reason) {
      case "Add 1" : {
        if(this.newName.value != "") {
          var type : Type = new Type();
          type.name = this.newName.value;
          this.accService.addType(type).subscribe(data => { this.types.push(data); this.newName.setValue("")});
        }          
        break;
      }
      case "Add 2" : {
        if(this.newName.value != "") {
          var category : Category = new Category();
          category.name = this.newName.value;
          this.accService.addCategory(category).subscribe(data => { this.categories.push(data); this.newName.setValue("")});
        }   
        break;
      }
      case "Add 3" : {
        if(this.newName.value != "" && this.newPrice.value != "") {
          var as : AdditionalService = new AdditionalService();
          as.name = this.newName.value;
          as.price = this.newPrice.value;
          as.included = true;
          this.accService.addAdditionalService(as).subscribe(data => { this.additionalServices.push(data); this.newName.setValue(""); this.newPrice.setValue("")});
        }   
        break;
      }
      case "Edit 1" : {
        if(this.newName.value != "") {
          var type : Type = new Type();
          type.id = this.editingId;
          type.name = this.newName.value;
          this.accService.updateType(type).subscribe(data => { this.newName.setValue("");
            this.types.forEach( t => {
              if(t.id == this.editingId) {
                t.name = type.name;
              }
            })
          });
        }   
        break;
      }
      case "Edit 2" : {
        if(this.newName.value != "") {
          var category : Category = new Category();
          category.id = this.editingId;
          category.name = this.newName.value;
          this.accService.updateCategory(category).subscribe(data => { this.newName.setValue("");
            this.categories.forEach( c => {
              if(c.id == this.editingId) {
                c.name = category.name;
              }
          })
          });
        }   
        break;
      }
      case "Edit 3" : {
        if(this.newName.value != "" && this.newPrice.value != "") {
          var as : AdditionalService = new AdditionalService();
          as.id = this.editingId;
          as.name = this.newName.value;
          as.price = this.newPrice.value;
          as.included = true;
          this.accService.updateAdditionalService(as).subscribe(data => { this.newName.setValue(""); this.newPrice.setValue(""); 
            this.additionalServices.forEach( a => {
              if(a.id == this.editingId) {
                a.name = as.name;
                a.price = as.price;
              }
            })
          });
        }   
        break;
      }
      case "Delete 1" : {
        this.accService.deleteType(this.editingId).subscribe(data => {
          var tt : Type = new Type();
          this.types.forEach(t => {
            if(t.id == this.editingId) {
              tt = t;
            }
          })
          const index: number = this.types.indexOf(tt);
          if (index !== -1) {
              this.types.splice(index, 1);
          }
        });
        break;
      }
      case "Delete 2" : {
        this.accService.deleteCategory(this.editingId).subscribe(data => {
          var cc : Category = new Category();
          this.categories.forEach(c => {
            if(c.id == this.editingId) {
              cc = c;
            }
          })
          const index: number = this.categories.indexOf(cc);
          if (index !== -1) {
              this.categories.splice(index, 1);
          }
        });
        break;
      }
      case "Delete 3" : {
        this.accService.deleteAdditionalService(this.editingId).subscribe(data => {
          var aa : AdditionalService = new AdditionalService();
          this.additionalServices.forEach(a => {
            if(a.id == this.editingId) {
              aa = a;
            }
          })
          const index: number = this.additionalServices.indexOf(aa);
          if (index !== -1) {
              this.additionalServices.splice(index, 1);
          }
        });
        break;
      }
      


    }
  }



  constructor(private accService: AccObjectService, private modalService: NgbModal) { }

  ngOnInit() {
    this.accService.getAllTypes().subscribe(data => { this.types = data; });
    this.accService.getAllCategories().subscribe(data => { this.categories = data; });
    this.accService.getAllAdditionalSevrices().subscribe(data => { this.additionalServices = data; });
  }

}

import { Pipe, PipeTransform } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { AdditionalService } from '../models/AdditionalService';
import { AdditionalservicesService } from '../services/additionalservices.service';
import { FilterObject } from '../models/FilterObject';


@Pipe({
    name: 'filter',
    pure: false
})
export class FilterPipe implements PipeTransform {

    constructor(private addService: AdditionalservicesService) {}

    transform(units: AccommodationUnit[], listFilter: FilterObject[]): AccommodationUnit[] {
      /* if(event != undefined || event != null) {
        let isChecked = event.srcElement.checked;
        
        let name = event.target.name;
        
        if(isChecked) {
            for(var i =0; i < units.length;i++) {
                for(var j = 0; j < units[i].additionalServices.length; j++) {
                    if(units[i].additionalServices[j].name === name) {
                        unn.push(units[i])
                    }
                }
            }
            return unn;
        } else {
            for(var i =0; i < units.length;i++) {
                for(var j = 0; j < units[i].additionalServices.length; j++) {
                    if(units[i].additionalServices[j].name != name) {
                        unn.push(units[i])
                    }
                }
            }
        }
       }  return units;*/
       console.log(listFilter.length);
       if(listFilter.length != 0) {
        listFilter.forEach(f => {
            if(f.include != true) {
                alert(listFilter.length);
                listFilter.splice(listFilter.indexOf(f), 1);
                alert(listFilter.length);
            }
        })
 
        console.log(listFilter);
       }
       return null;
    }

}
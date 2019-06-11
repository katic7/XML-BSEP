import { Pipe, PipeTransform } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { AdditionalService } from '../models/AdditionalService';
import { AdditionalservicesService } from '../services/additionalservices.service';
import { FilterObject } from '../models/FilterObject';
import { AccommodationunitService } from '../services/accommodationunit.service';
import { filter } from 'rxjs/operators';


@Pipe({
    name: 'filter',
    pure: false
})
export class FilterPipe implements PipeTransform {
    
    transform(units: AccommodationUnit[], listFilter: FilterObject[]): AccommodationUnit[] {
        var unn:AccommodationUnit[] = [];
        let filters:FilterObject[] = [];
        var brojac = 0;
        listFilter.forEach(ll => {
            filters.push(ll);
        })
        
        
        if(listFilter.length != 0) {

            for(let i = 0; i < units.length; i++) {
                brojac = 0;
                listFilter.forEach(lf => {
                    
                    for(let j = 0; j < units[i].additionalServices.length; j++) { 
                        if(units[i].additionalServices[j].name == lf.name) {
                          
                          brojac += 1;
                        } 
                    }
                    
                })
                if(brojac == listFilter.length) {
                    if(unn.indexOf(units[i]) == -1) {
                        unn.push(units[i]);
                    }   
                }
                
            }
  
            return unn;
        } else {
            return units;
        }
    } 
}
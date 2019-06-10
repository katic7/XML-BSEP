import { Pipe, PipeTransform } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { AdditionalService } from '../models/AdditionalService';
import { AdditionalservicesService } from '../services/additionalservices.service';
import { FilterObject } from '../models/FilterObject';
import { AccommodationunitService } from '../services/accommodationunit.service';


@Pipe({
    name: 'filter',
    pure: false
})
export class FilterPipe implements PipeTransform {

    transform(units: AccommodationUnit[], listFilter: FilterObject[], globa): AccommodationUnit[] {
        var unn:AccommodationUnit[] = [];
        units = globa;
        if(listFilter.length != 0) {
            listFilter.forEach(lf => {
                for(var i =0; i < units.length;i++) {
                    for(var j = 0; j < units[i].additionalServices.length; j++) {
                        if(units[i].additionalServices[j].name == lf.name) {
                            unn.push(units[i]);
                           
                        } else {
                            break;
                        }
                    }
                }       
            })
            
            return unn;
        } else {
            return units;
        }
    } 
}
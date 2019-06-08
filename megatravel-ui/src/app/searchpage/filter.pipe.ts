import { Pipe, PipeTransform } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { AdditionalService } from '../models/AdditionalService';

@Pipe({
    name: 'filter',
    pure: false
})
export class FilterPipe implements PipeTransform {

    transform(units: AccommodationUnit[], filt:AdditionalService): AccommodationUnit[] {
        
        if(filt != null) {
            console.log(units[0].additionalServices.includes(filt))
            let unn :AccommodationUnit[] = [];
            for(var i =0; i < units.length;i++) {
                for(var j = 0; j < units[i].additionalServices.length; j++) {
                    if(units[i].additionalServices[j].name === filt.name) {
                        unn.push(units[i])
                    }
                }
            }
            return unn;
        } return units;
        
    }

}
import { Pipe, PipeTransform } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';

@Pipe({
    name: 'filter',
    pure: false
})
export class FilterPipe implements PipeTransform {

    transform(units: AccommodationUnit[], filt:string): AccommodationUnit[] {
        if(filt == '') {
            return units;
        } else {
        return units.filter(function (el) {
            return el.additionalServices.forEach(e => e.name === filt)
          });
        }
    }
}
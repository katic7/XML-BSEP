import { Pipe, PipeTransform } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { AdditionalService } from '../models/AdditionalService';
import { AdditionalservicesService } from '../services/additionalservices.service';
import { FilterObject } from '../models/FilterObject';
import { AccommodationunitService } from '../services/accommodationunit.service';
import { filter } from 'rxjs/operators';
import { DestinationObject } from '../models/DestinationObject';
import { Address } from '../models/Address';

@Pipe({
    name: 'destinationfilter',
    pure: false
})
export class DestinationFilter implements PipeTransform {
    
    transform(units: AccommodationUnit[], destObj: DestinationObject, listaAdresa:Address[]): AccommodationUnit[] {
        let unn : AccommodationUnit[] = [];
        if( destObj.distanceO != null) {
           for(var i = 0; i < listaAdresa.length; i++) {
                let distanceInKm = this.calcCrow(destObj.latitude, destObj.longitude, listaAdresa[i][0].latitude, listaAdresa[i][0].longitude);
                if(destObj.distanceO.unit == "m") {
                    
                    let distanceInM = distanceInKm*1000;
                    if(destObj.distanceO.distance < distanceInM) {       
                      return units.filter(u => u.accommodationObject.addressId = listaAdresa[i][0].id);
                    }
                } else {
                    if(destObj.distanceO.distance < distanceInKm) {         
                     return units.filter(u => u.accommodationObject.addressId = listaAdresa[i][0].id);
                    }
                }
            }
        } else {
            return units;
        }  
    }


     calcCrow(lat1, lon1, lat2, lon2) 
    {
        console.log(lat1, lon1, lat2, lon2);
      var R = 6371; // km
      var dLat = this.toRad(lat2-lat1);
      var dLon = this.toRad(lon2-lon1);
      var lat1d = this.toRad(lat1);
      var lat2d = this.toRad(lat2);

      var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1d) * Math.cos(lat2d); 
      var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
      var d = R * c;
      return d;
    }

    // Converts numeric degrees to radians
     toRad(Value) 
    {
        return Value * Math.PI / 180;
    }
}
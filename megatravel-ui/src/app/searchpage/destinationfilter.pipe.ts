import { Pipe, PipeTransform } from '@angular/core';
import { AccommodationUnit } from '../models/AccommodationUnit';
import { AdditionalService } from '../models/AdditionalService';
import { AdditionalservicesService } from '../services/additionalservices.service';
import { FilterObject } from '../models/FilterObject';
import { AccommodationunitService } from '../services/accommodationunit.service';
import { filter } from 'rxjs/operators';
import { DestinationObject } from '../models/DestinationObject';
import { Address } from '../models/Address';
import { DestinationSorter } from '../models/DestinationSorter';

@Pipe({
    name: 'destinationfilter',
    pure: false
})
export class DestinationFilter implements PipeTransform {
    distancesCalculated:DestinationSorter[]=[];
    tempArray: DestinationSorter[] = [];
    transform(units: AccommodationUnit[], destObj: DestinationObject, listaAdresa:Address[], distArray: DestinationSorter[]): AccommodationUnit[] {
        distArray = [];
        let unn : AccommodationUnit[] = [];
        if( destObj.distanceO != null) {
           for(var i = 0; i < listaAdresa.length; i++) {
                let distanceInKm = this.calcCrow(destObj.latitude, destObj.longitude, listaAdresa[i].latitude, listaAdresa[i].longitude);
                
                
                
                if(destObj.distanceO.unit == "m") {
                    
                    let distanceInM = distanceInKm*1000;
                    if(destObj.distanceO.distance > distanceInM) {       
                      //return units.filter(u => u.accommodationObject.addressId == listaAdresa[i].id);
                      units.forEach(u => {
                           if(u.accommodationObject.addressId == listaAdresa[i].id) {
                               unn.push(u);
                               let o:DestinationSorter=new DestinationSorter();
                               o.unitId = u.id;
                               o.distanceInkm = distanceInKm;
                            //    //this.distancesCalculated.forEach(dc => 
                            //    // {
                            //        // if(o.addressId != listaAdresa[i].id) {
                            //             this.distancesCalculated.push(o);
                            //       //  }
                            //    // })

                                var br = 0;
                                distArray.forEach(d => {
                                    if(d.unitId == o.unitId) {
                                        br = 1;
                                    }
                                })
                                if(br == 0) {
                                    distArray.push(o);
                                }
                                //console.log(o);
                           }
                        
                    })
                    }
                } else {
                    if(destObj.distanceO.distance > distanceInKm) {         
                     //return units.filter(u => u.accommodationObject.addressId == listaAdresa[i].id);
                     units.forEach(u => {
                        if(u.accommodationObject.addressId == listaAdresa[i].id) {
                            unn.push(u);
                            let o:DestinationSorter = new DestinationSorter();
                            o.unitId = u.id;
                            o.distanceInkm = distanceInKm;
                            //   // this.distancesCalculated.forEach(dc => 
                            //    // {
                            //       //  if(dc.addressId != listaAdresa[i].id) {
                            //             this.distancesCalculated.push(o);
                            //        // }
                            //     //})
                            var br = 0;
                            distArray.forEach(d => {
                                if(d.unitId == o.unitId) {
                                    br = 1;
                                }
                            })
                            if(br == 0) {
                                distArray.push(o);
                            }
                            
                                //console.log(o);
                        }
                     })
                    }
                }
            }

            /*let tempArray: DestinationSorter[] = [];
            for(var k=0; k < units.length;k++) {
                tempArray.push(this.distancesCalculated[k]);
            }*/
           
            /*tempArray.forEach(dd=>{
                distArray.push(dd);
            })*/
          //  distArray = this.distancesCalculated;
        //    distArray.forEach(d => {
        //        console.log(d);
        //    })
        console.log(distArray);
        this.tempArray = distArray;
            return unn;
        } else {
            return units;
        }  
    }

     calcCrow(lat1, lon1, lat2, lon2) 
    {
       
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

    returnDistances() {
        return this.tempArray;
    }
}
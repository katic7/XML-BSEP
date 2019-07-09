import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Address } from 'src/app/models/Address';
import { Observable } from 'rxjs';
import { AccommodationObject } from 'src/app/models/AccommodationObject';

@Injectable({
  providedIn: 'root'
})
export class CreateObjectService {

  constructor(private http : HttpClient) { }


  getLocation(adr : String){
    return this.http.get('https://nominatim.openstreetmap.org/search?q="+'+adr+'"&format=json')
  }

  createAddress(adr : Address) :Observable<any>{
    return this.http.post('https://localhost:8081/agentservice/api/accommodations/createAddress',adr);
  }

  createObject(accObj: AccommodationObject) : Observable<any>{
    return this.http.post('https://localhost:8081/agentservice/api/accommodations/createAccObject', accObj);
  }

  getAllTypes():Observable<any>{
    return this.http.get('https://localhost:8081/agentservice/api/accommodations/types');
  }

  getAllCategories():Observable<any>{
    return this.http.get('https://localhost:8081/agentservice/api/accommodations/categories');
  }
}

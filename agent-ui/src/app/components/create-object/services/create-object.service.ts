import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Address } from 'src/app/models/Address';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateObjectService {

  constructor(private http : HttpClient) { }


  getLocation(adr : String){
    return this.http.get('https://nominatim.openstreetmap.org/search?q="+'+adr+'"&format=json')
  }

  createAddress(adr : Address) :Observable<any>{
    return this.http.post('https://localhost:8091/api/accommodations/createAddress',adr);
  }

}

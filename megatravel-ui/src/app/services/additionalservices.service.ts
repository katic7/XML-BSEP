import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdditionalservicesService {

  constructor(private http: HttpClient) { }

  getAdditionalByName(name) : Observable<any>{
   return this.http.get("https://localhost:8082/api/accobject/additionalservices/"+name);
  }

  getAllAdditionalServices():Observable<any> {
    return this.http.get("https://localhost:8091/api/accommodations/allAdditionalServices");
  }
}

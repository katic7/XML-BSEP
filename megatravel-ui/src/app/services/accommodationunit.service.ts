import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AccommodationunitService {

  constructor(private http: HttpClient) { }

  getProfilePicture(id) : Observable<any>{
   return this.http.get("http://localhost:8082//ratings/specificAccommodation/"+id);
  }

  getOne(id) : Observable<any> {
    return this.http.get("http://localhost:8082/api/accobject/getOneUnit/" + id);
  }
}

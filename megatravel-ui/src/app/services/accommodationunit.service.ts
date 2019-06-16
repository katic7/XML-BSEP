import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AccommodationunitService {

  constructor(private http: HttpClient) { }

  getProfilePicture(id) : Observable<any>{
   return this.http.get("https://localhost:8082/ratings/specificAccommodation/"+id);
  }

  getOne(id) : Observable<any> {
    return this.http.get("https://localhost:8082/api/accobject/getOneUnit/" + id);
  }

  getRatingScore(id):Observable<any> {
    return this.http.get("https://localhost:8082/api/comment/ratings/specificAccommodation/"+id)
  }


  getDestinationInfo(street) : Observable<any> {
    return this.http.get("https://nominatim.openstreetmap.org/search?q="+street+"&format=json");
  }

  getAddress(id):Observable<any> {
    return this.http.get("https://localhost:8082/api/addresses/" + id);
  }

}
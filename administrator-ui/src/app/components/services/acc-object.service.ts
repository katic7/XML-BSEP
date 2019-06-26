import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccObjectService {

  constructor(private http : HttpClient) { }

  getAccObjects() : Observable<any>{  
    return this.http.get('//localhost:8082/api/accobject/getAllwOutAgent'); //sve koji nemaju agenta       STA JE OVO
  }

  getAllTypes() : Observable<any> {
    return this.http.get('https://localhost:8082/api/accobject/types');
  }

  getAllCategories() : Observable<any> {
    return this.http.get('https://localhost:8082/api/accobject/categories');
  }

  getAllAdditionalSevrices() : Observable<any> {
    return this.http.get('https://localhost:8082/api/accobject/additionalservices');
  }

}

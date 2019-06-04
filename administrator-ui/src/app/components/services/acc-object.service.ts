import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccObjectService {

  constructor(private http : HttpClient) { }

  getAccObjects() : Observable<any>{  
    return this.http.get('//localhost:8082/api/accobject/getAllwOutAgent'); //sve koji nemaju agenta
  }
}

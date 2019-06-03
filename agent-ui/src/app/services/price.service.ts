import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AccUnitPrice } from '../models/AccUnitPrice';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PriceService {

  constructor(private http : HttpClient) { }

  addNewPrice(price : AccUnitPrice) : Observable<any>{
    return this.http.post('//localhost:8085/api/', price);   
  }

}

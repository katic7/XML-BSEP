import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Price } from '../models/Price';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PriceService {

  constructor(private http : HttpClient) { }

  addNewPrice(price : Price) : Observable<any>{
    return this.http.post('//localhost:8085/api/', price);    //PRILAGODI BEKENND APIU
  }

}

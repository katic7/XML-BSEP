import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Type } from 'src/app/models/Type';
import { Category } from 'src/app/models/Category';
import { AdditionalService } from 'src/app/models/AdditionalService';

@Injectable({
  providedIn: 'root'
})
export class AccObjectService {

  constructor(private http : HttpClient) { }

  getAccObjects() : Observable<any>{  
    return this.http.get('//localhost:8081/accommodationservice/api/accobject/getAllwOutAgent'); //sve koji nemaju agenta       STA JE OVO
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

  addType(info : Type) : Observable<any> {
    return this.http.post('https://localhost:8081/accommodationservice/api/accobject/types', info);
  }

  addCategory(info : Category) : Observable<any> {
    return this.http.post('https://localhost:8081/accommodationservice/api/accobject/categories', info);
  }

  addAdditionalService(info : AdditionalService) : Observable<any> {
    return this.http.post('https://localhost:8081/accommodationservice/api/accobject/additionalservices', info);
  }
  updateType(info : Type) : Observable<any> {
    return this.http.post('https://localhost:8081/accommodationservice/api/accobject/types/' + info.id, info);
  }

  updateCategory(info : Category) : Observable<any> {
    return this.http.post('https://localhost:8081/accommodationservice/api/accobject/categories/' + info.id, info);
  }

  updateAdditionalService(info : AdditionalService) : Observable<any> {
    return this.http.post('https://localhost:8081/accommodationservice/api/accobject/additionalservices/' + info.id, info);
  }

  deleteType(id) : Observable<any> {
    return this.http.delete('https://localhost:8081/accommodationservice/api/accobject/types/' + id);
  }

  deleteCategory(id) : Observable<any> {
    return this.http.delete('https://localhost:8081/accommodationservice/api/accobject/categories/' + id);
  }

  deleteAdditionalService(id) : Observable<any> {
    return this.http.delete('https://localhost:8081/accommodationservice/api/accobject/additionalservices/' + id);
  }

}

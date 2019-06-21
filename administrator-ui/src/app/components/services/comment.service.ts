import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ "Allow-Origin": "*"})
};

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http : HttpClient) { }

  getAllComments() : Observable<any> {
    return this.http.get('https://localhost:8081/accommodationservice/api/comment/ratings');   //uskladiti endpojnte!
  }

  setCommentVisible(obj) : Observable<any>{
    console.log(obj);
    return this.http.post('https://localhost:8081/accommodationservice/api/comment/visibility', obj);
  }

}

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
    return this.http.get('http://localhost:8134/getAllRatings', httpOptions);   //uskladiti endpojnte!
  }

  setCommentVisible(obj) : Observable<any>{
    console.log(obj);
    return this.http.post('http://localhost:8139/publishComment', obj, httpOptions);
  }

}

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http : HttpClient) { }

  getAllComments() : Observable<any> {
    return this.http.get('//localhost:8085/api/comment/getAll');   //uskladiti endpojnte
  }

  setCommentVisible(comment : Comment) : Observable<any>{
    return this.http.post('//localhost:8085/api/comment/visibility', comment);
  }

}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  constructor(private http : HttpClient) { }

  addAgent(id ) : Observable<any>{    //IZMENITI KAD BUDE BEKEND
    return this.http.post('//localhost:8085/api/agent/add', id);
  }
}

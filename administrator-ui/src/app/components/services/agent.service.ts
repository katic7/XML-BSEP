import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateAgent } from 'src/app/models/CreateAgent';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  constructor(private http : HttpClient) { }

  addAgent(createAgent : CreateAgent) : Observable<any>{    
    return this.http.post('//localhost:8085/api/auth/createAgent', createAgent);
  }

  getAll() : Observable<any>{
    return this.http.get('//localhost:8085/api/auth/getAgents');
  }

  getOneAgent(id: number) : Observable<any>{
    return this.http.get('//localhost:8085/api/auth/getOneAgent/'+id);
  }
}

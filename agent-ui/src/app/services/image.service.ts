import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http:HttpClient) { }

  postFile( fileToUpload: File[], id: Int16Array) {
    const formData: FormData = new FormData();
    for(var i =0; i<fileToUpload.length;i++){
      formData.append("Image", fileToUpload[i]);
    }
    return this.http.post('https://localhost:8081/agentservice/api/accommodations/uploadImage/'+id, formData);
  }
}

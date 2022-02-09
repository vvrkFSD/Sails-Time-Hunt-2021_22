import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ForgetPdService {
  constructor(private http: HttpClient) {}
  postEmail(data: any) {
    // console.log(data, 'service here');
    return this.http.post<any>('http://localhost:3000/posts', data);
  }
}

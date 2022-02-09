import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ResetPdService {
  constructor(private http: HttpClient) {}
  postPasswordData(data: any): Observable<any> {
    // console.log(data, 'service here');
    return this.http.post<any>('http://localhost:3000/posts', data);
  }
}

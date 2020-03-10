import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestService {
  baseUrl = "/api";

  constructor(private http: HttpClient) { }

  testApi() : Observable<any> {
      return this.http.get(`/api/works`);
  }

}
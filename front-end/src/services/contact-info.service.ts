import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ContactInfo} from '../app/models/contatct-info';

@Injectable({
  providedIn: 'root'
})
export class ContactInfoService {

  baseUrl: string = 'http://localhost:9090/Contact';
  constructor(private http: HttpClient) { }

  saveContactInfo(contactInfo: ContactInfo): Observable<any> {
    const token = sessionStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.post(
      `${this.baseUrl}/save`,
      contactInfo,
      { headers }
    );
  }

}

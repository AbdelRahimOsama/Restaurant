import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Chef} from '../app/models/chef';

@Injectable({
  providedIn: 'root'
})
export class ChefService {

  baseurl = 'http://localhost:9090/chef';
  constructor(private http: HttpClient) { }

  getchefs(): Observable<Chef[]>{
    return this.http.get<Chef[]>(this.baseurl + '/team').pipe(
      res => res
    );
  }
}

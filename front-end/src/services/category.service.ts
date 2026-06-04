import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Category} from '../app/models/category';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  baseurl = 'http://localhost:9090/category';

  constructor(private http: HttpClient) { }

  getallcategory(): Observable<Category[]>{
    return this.http.get<Category[]>(this.baseurl + '/allCategory').pipe(
      map(
        res => res
      )
    );
  }
}

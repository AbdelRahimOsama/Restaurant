import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Category} from '../../app/models/category';
import {map} from 'rxjs/internal/operators';
import {CardService} from '../card.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  baseurl = 'http://localhost:9090/auth/';

  constructor(private cardService : CardService,private http: HttpClient) { }

  signUp(username,password): Observable<any>{
    return this.http.post<any>(this.baseurl + 'sign_up',{username,password}).pipe(
      map(
        res => res
      )
    );
  }
  logIn(username,password): Observable<any>{
    return this.http.post<any>(this.baseurl + 'login',{username,password}).pipe(
      map(
        res => res
      )
    );
  }

  isUserLogIn(){
    return sessionStorage.getItem('token');
  }

  isUserAdmin(): boolean {
    try {
      const roles = JSON.parse(sessionStorage.getItem('roles') || '[]');
      return roles.some((r: any) => r.roleName === 'ADMIN');
    } catch {
      return false;
    }
  }

  logOut(){
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('roles');
    this.cardService.doneOrder()
  }
}

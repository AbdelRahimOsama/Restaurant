import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from '../Security/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginSignupGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.authService.isUserLogIn()){
      this.router.navigateByUrl('/products');
      return false;
    }
    return true;
  }

}

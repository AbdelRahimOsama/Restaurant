import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from '../Security/auth.service';
import {ActivatedRoute, Router} from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService,private activatedRoute: ActivatedRoute, private router: Router) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(this.authService.isUserLogIn()) {
      let login = this.activatedRoute.snapshot.paramMap.has('login');
      let signup = this.activatedRoute.snapshot.paramMap.has('sign_up');
      if(login || signup){
        this.router.navigateByUrl('/products');
      }
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${sessionStorage.getItem('token')}`
        }
      })
    }
    return next.handle(request);
  }

}

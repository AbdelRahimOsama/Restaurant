import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../services/Security/auth.service';
import {CardService} from '../services/card.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private authService: AuthService,private cardservice: CardService,private router : Router) {

 }

 isLogIn() {
   return this.authService.isUserLogIn();
 }

 IsShowMenu(){
    return this.router.url.includes('products') || this.router.url.includes('category');
 }

}

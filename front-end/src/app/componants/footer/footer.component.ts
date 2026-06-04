import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../services/Security/auth.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(private authService: AuthService,) { }

  ngOnInit(): void {
  }
  isUserLogIn(){
    return this.authService.isUserLogIn();
  }
}

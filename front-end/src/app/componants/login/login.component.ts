import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../services/Security/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isUsernameValid: boolean = false;
  isPasswordValid: boolean = false;
  isConfirmPasswordValid: boolean = false;
  isPasswordMatching: boolean = false;
  massage_ar : string='';
  massage_en : string='';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  checkCustomer(username,password){
    if(!this.validateInputs(username,password)){
      return;
    }
    this.authService.logIn(username,password).subscribe(
      res => {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('id');
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('roles');

        sessionStorage.setItem('token',res.token);
        sessionStorage.setItem('id',res.id);
        sessionStorage.setItem('username',res.username);
        sessionStorage.setItem('roles', JSON.stringify(res.roleDtos));
        this.router.navigateByUrl('/products');
      },errorResponse => {
        this.massage_ar = errorResponse.error.message_ar;
        this.massage_en = errorResponse.error.message_en;
      }
    );
  }

  private validateInputs(username: string, password: string) {

    if(!password){
      this.isPasswordValid = true;
      return false;
    }

    if(!username ){
      this.isUsernameValid = true;
      return false;
    }

    return true;
  }

  clearUsername(){
    this.massage_ar = '';
    this.massage_en = '';
    this.isUsernameValid = false;
  }

  clearPassword(){
    this.massage_ar = '';
    this.massage_en = '';
    this.isPasswordValid = false;
  }
  showPassword: boolean = false;

  togglePassword() {
    this.showPassword = !this.showPassword;
  }
}

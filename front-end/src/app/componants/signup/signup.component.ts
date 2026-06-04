import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../services/Security/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  isUsernameValid: boolean = false;
  isPasswordValid: boolean = false;
  isConfirmPasswordValid: boolean = false;
  isPasswordMatching: boolean = false;
  massage_ar : string='';
  massage_en : string='';

  constructor(private authService : AuthService,private router: Router) {

  }

  ngOnInit(): void {
  }

  showPassword: boolean = false;

  togglePassword() {
    this.showPassword = !this.showPassword;
  }
  showPassword2: boolean = false;

  togglePassword2() {
    this.showPassword2 = !this.showPassword2;
  }



  createCustomer(username: string, password: string, confirmPassword: string) {
    if (!this.validateInputs(username, password, confirmPassword)) {
      return;
    }

    this.authService.signUp(username, password).subscribe(
      res => {
        sessionStorage.removeItem('id');
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('roles');

        sessionStorage.setItem('id',res.id);
        sessionStorage.setItem('token',res.token);
        sessionStorage.setItem('username',res.username);
        sessionStorage.setItem('roles', JSON.stringify(res.roleDtos));
        this.router.navigateByUrl('/products');
      },
      errorResponse => {
        console.log("FULL ERROR:", errorResponse);
        console.log("ERROR BODY:", errorResponse.error);
        this.massage_en = errorResponse.error?.message_en;
        this.massage_ar = errorResponse.error?.message_ar;
      }
    );
  }

  private validateInputs(username: string, password: string, confirmPassword: string) {
    if(!password){
      this.isPasswordValid = true;
      return false;
    }
    if(!username ){
      this.isUsernameValid = true;
      return false;
    }
    if(!confirmPassword){
      this.isConfirmPasswordValid = true;
      return false;
    }
    if (password!==confirmPassword){
      this.isPasswordMatching = true;
      return false;
    }
    return true;
  }

  clearConfirmPassword(){
    this.massage_ar = '';
    this.massage_en = '';
    this.isConfirmPasswordValid = false;
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

}

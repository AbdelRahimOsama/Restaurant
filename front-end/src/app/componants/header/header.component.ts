import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../services/category.service';
import {Category} from '../../models/category';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../../services/Security/auth.service';
import {CustomerDetails} from '../../models/customer-details';
import {CustomerDetailsService} from '../../../services/customer-details.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent{

  categories: Category[];
  customerDetails: CustomerDetails;
  constructor(private categoryService: CategoryService,private route: Router,private authService: AuthService,private router: Router,private customerDetailsService: CustomerDetailsService) {
  }

  ngOnInit(): void {
  }

  search(key){
    if (key && key.trim() !== "") {
      this.route.navigateByUrl("/search/" + key);
    }else{
      this.route.navigateByUrl("/products");
    }
  }

  getUserName(){
    return sessionStorage.getItem('username');
  }

  isUserAdmin(){
    return this.authService.isUserAdmin();
  }

  isUserLogIn(){
    return this.authService.isUserLogIn();
  }

  logOut(){
    this.authService.logOut();
    this.route.navigateByUrl("/login");
  }
  ActionClick(action){
    if(action == 'RequestOrder'){
        this.route.navigateByUrl('/requestMyOrders');
    }else if(action == 'UpdateProfile'){
      sessionStorage.removeItem('customerDetails');
      this.customerDetailsService.loadCustomerDetalis().subscribe(response => {
        this.customerDetails = response;
        sessionStorage.setItem(
          "customerDetails",
          JSON.stringify(this.customerDetails)
        );
        this.route.navigateByUrl('/profile/update');
      });
    }else if(action == 'RequestAllOrder'){
      this.route.navigateByUrl('/requestAllOrders');
      debugger
    }else{
      this.route.navigateByUrl('/product/add');
    }
  }
  IsShowMenu(){
    return this.router.url.includes('products') || this.router.url.includes('category') || this.router.url.includes('search');
  }
}

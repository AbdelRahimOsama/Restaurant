import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CustomerDetailsService} from '../../../services/customer-details.service';
import {CustomerDetails} from '../../models/customer-details';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private router: Router,private customerDetailsService: CustomerDetailsService) {
  }

  customerDetails : CustomerDetails = new CustomerDetails() ;

  ngOnInit() {
      this.customerDetailsService.loadCustomerDetalis().subscribe(
        response => {
          this.customerDetails = response;
        },error => {
          this.customerDetails = new CustomerDetails();
        }
      )
  }
  getUserName(){
    return sessionStorage.getItem('username');
  }
  defaultImage = '../img/about-1.png';

  onImageError(event: Event): void {
    (event.target as HTMLImageElement).src = this.defaultImage;
  }

  onUpdate(): void {
    this.router.navigate(['/profile/update']);
  }
  onBack(): void { this.router.navigate(['/']); }
}

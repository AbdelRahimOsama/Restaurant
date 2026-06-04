import { Component, OnInit } from '@angular/core';
import {CustomerDetails} from '../../models/customer-details';
import {Router} from '@angular/router';
import {CustomerDetailsService} from '../../../services/customer-details.service';
import {Customer} from '../../models/customer';
import {error} from 'protractor';
import {aliasTransformFactory} from '@angular/compiler-cli/src/ngtsc/transform';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  customerDetails: CustomerDetails=new CustomerDetails();
  massage_en='';
  massage_ar='';
  constructor(private customerDetailsService: CustomerDetailsService, private router: Router) { }

  ngOnInit(): void {
    this.customerDetailsService.loadCustomerDetalis().subscribe(
      res=>{
        this.customerDetails = res;
      },errorResponse => {
        this.customerDetails = new CustomerDetails();
        this.massage_ar = errorResponse.error.message_ar;
        this.massage_en = errorResponse.error.message_en;
        console.log(this.massage_en);
        console.log(this.massage_en);
      }
    )
  }
  selectedFile!: File;

  onFileSelected(event: any) {

    const file = event.target.files[0];

    if (file) {

      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => {
        this.customerDetails.imagePath = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  onSave(name, email, phone, age, address) {

    const formData = new FormData();
    formData.append('name', name);
    formData.append('email', email);
    formData.append('phone', phone);
    formData.append('age', age);
    formData.append('address', address);

    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }
    if(this.customerDetails!=null) {
      this.customerDetailsService
        .updatecustomerDetails(formData)
        .subscribe(res => {
          this.router.navigateByUrl('/profile');
        },errorResponse => {
          this.customerDetails = new CustomerDetails();

          this.massage_ar = errorResponse.error.message_ar;
        this.massage_en = errorResponse.error.message_en;

      });
    }else{
      this.customerDetailsService.addCustomerDetails(formData).subscribe(res => {
        this.router.navigateByUrl('/profile');
      },errorResponse => {
        this.customerDetails = new CustomerDetails();
        this.massage_ar = errorResponse.error.message_ar;
        this.massage_en = errorResponse.error.message_en;
      });
    }
    console.log(this.massage_en);
    console.log(this.massage_ar);

  }
  onBack(){
    this.router.navigateByUrl('/profile');
  }

}

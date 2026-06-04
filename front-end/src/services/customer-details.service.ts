import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CustomerDetails} from '../app/models/customer-details';

@Injectable({
  providedIn: 'root'
})
export class CustomerDetailsService {

  constructor(private http: HttpClient) { }

  baseurl: string='http://localhost:9090/CustomerDetails';

  loadCustomerDetalis(): Observable<any> {
    const customerId = sessionStorage.getItem("id");
    return this.http.get<CustomerDetails>(
      `${this.baseurl}/${customerId}`
    );
  }

  updatecustomerDetails(formData: FormData) {
    const customerId = sessionStorage.getItem("id");
    return this.http.patch(
      `${this.baseurl}/update/${customerId}`,
      formData
    );
  }

  addCustomerDetails(formData: FormData) {
    const customerId = sessionStorage.getItem("id");
    return this.http.post(
      `${this.baseurl}/add/${customerId}`,
      formData
    )
  }
}

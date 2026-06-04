import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent {

  details = {
    ingredients: '',
    calories: '',
    size: '',
    description: ''
  };

  productId!: number;

  constructor(private http: HttpClient) {}

  addDetails() {
    this.http.post(`http://localhost:9090/product-details/${this.productId}`, this.details)
      .subscribe(res => {
        alert("Product Details Added!");
      });
  }

  updateDetails(id: number) {
    this.http.put(`http://localhost:9090/product-details/${id}`, this.details)
      .subscribe(res => {
        alert("Updated!");
      });
  }

  deleteDetails(id: number) {
    this.http.delete(`http://localhost:9090/product-details/${id}`)
      .subscribe(res => {
        alert("Deleted!");
      });
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Product} from '../app/models/product';
import {Observable, pipe} from 'rxjs';
import {map} from 'rxjs/internal/operators';
export interface ProductsResponse {
  products: Product[];
  totalproducts: number;
}
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseurl = 'http://localhost:9090/product';

  constructor(private http: HttpClient) {
  }

  getallproducts(pageNumber: number, pageSize: number): Observable<ProductsResponse> {
    return this.http.get<ProductsResponse>(
      `${this.baseurl}/getAllProducts/pageNum/${pageNumber}/pageSize/${pageSize}`
    );
  }
  deleteProduct(id){
    return this.http.delete(`${this.baseurl}/deleteProduct?id=${id}`);
  }
  // تعديل: أضف pageNumber و pageSize
  search(key: string, pageNumber: number, pageSize: number): Observable<ProductsResponse> {
    return this.http.get<ProductsResponse>(
      `${this.baseurl}/searchProduct/${key}/pageNum/${pageNumber}/pageSize/${pageSize}`
    );
  }

  getProductsByCategoryId(categoryId: string, pageNumber: number, pageSize: number): Observable<ProductsResponse> {
    return this.http.get<ProductsResponse>(
      `${this.baseurl}/getAllProductsByCategoryId/${categoryId}/pageNum/${pageNumber}/pageSize/${pageSize}`
    );

  }
  updateProducr(formData : FormData){
    return this.http.patch(`${this.baseurl}/patchupdateProduct`, formData);
  }

  addProduct(formData : FormData){
    return this.http.post(`${this.baseurl}/saveProduct`, formData);
  }
}

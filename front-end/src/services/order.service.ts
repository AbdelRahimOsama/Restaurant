import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Cardorder} from '../app/models/cardorder';
import {Observable} from 'rxjs';
import {RequistOrder} from '../app/models/vm/requistOrder';
import {Product} from '../app/models/product';
import {DataOrder} from '../app/models/vm/dataOrder';
import {ProductsResponse} from './product.service';

export interface ResponseOrder {
  orders: DataOrder[];
  totalorders: number;
}

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  baseUrl: string = 'http://localhost:9090/order/';

  constructor(private http : HttpClient) {}

  addOrders(order: Cardorder[], customerId: number, totalNumber: number,
            totalPrice: number, tableNumber: number): Observable<any> {

    const orderItemDtos: RequistOrder[] = order.map(item => {

      const product = new Product(
        item.id,
        item.name,
        item.description,
        item.price,
        item.imagePath
      );
      return new RequistOrder(
        product,
        item.quantity ?? 1
      );
    });
    return this.http.post<any>(
      this.baseUrl + 'save',
      {
        orderItemDtos,
        customerId,
        totalNumber,
        totalPrice,
        tableNumber
      }
    );
  }

  getAllMyOrders(page,pageLength) :Observable<ResponseOrder> {
    const customerId=sessionStorage.getItem("id");
    return this.http.get<ResponseOrder>(this.baseUrl + 'myorder/'+customerId+'/pageNum/'+page+'/pageSize/'+pageLength).pipe(
      res => res
    );
  }

  getAllOrders(page,pageLength){
    return this.http.get<ResponseOrder>(this.baseUrl +'pageNum/'+page+'/pageSize/'+pageLength).pipe(
      res => res
    );
  }
}

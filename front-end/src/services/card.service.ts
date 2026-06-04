import { Injectable } from '@angular/core';
import {Cardorder} from '../app/models/cardorder';
import {BehaviorSubject, Subject} from 'rxjs';
import {OrderService} from './order.service';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  orders: Cardorder[] = [];

  totalPrice: BehaviorSubject<number> = new BehaviorSubject(0);
  totalSize: BehaviorSubject<number> = new BehaviorSubject(0);

  constructor(private orderService: OrderService) {
    this.loadFromStorage();
  }

  loadFromStorage() {
    this.orders = JSON.parse(sessionStorage.getItem('orders') || '[]');
    this.calTotalPrice();
  }

  addProductToOrder(neworder: Cardorder) {

    let item = this.orders.find(o => o.id === neworder.id);

    if (item) {
      item.quantity++;
    } else {
      neworder.quantity = 1;
      this.orders.push(neworder);
    }
    this.calTotalPrice();
  }

  changtoplus(order: Cardorder) {

    let item = this.orders.find(e => e.id === order.id);

    if (item) {
      item.quantity++;
    }

    this.calTotalPrice();
  }

  changToMinus(order: Cardorder) {

    let index = this.orders.findIndex(e => e.id === order.id);
    if (index !== -1) {
      this.orders[index].quantity--;
      if (this.orders[index].quantity <= 0) {
        this.orders.splice(index, 1);
      }
    }
    this.calTotalPrice();
  }

  calTotalPrice() {

    let totalPrice = 0;
    let totalSize = 0;

    for (let e of this.orders) {
      totalSize += e.quantity;
      totalPrice += e.price * e.quantity;
    }

    this.totalPrice.next(totalPrice);
    this.totalSize.next(totalSize);

    sessionStorage.setItem("orders", JSON.stringify(this.orders));
    sessionStorage.setItem("totalPrice", totalPrice.toString());
    sessionStorage.setItem("totalSize", totalSize.toString());
  }
 //,customerId,totalNumber,totalPrice,tableNumber
  doneOrder() {

    this.orders = [];
    this.totalPrice.next(0);
    this.totalSize.next(0);
    sessionStorage.setItem("orders", JSON.stringify([]));
    sessionStorage.setItem("totalPrice", "0");
    sessionStorage.setItem("totalSize", "0");
  }

  removeOreder(order){
    let index = this.orders.findIndex(e => e.id === order.id);
    if (index !== -1) {
        this.orders.splice(index, 1);
    }
    this.calTotalPrice();
  }

}

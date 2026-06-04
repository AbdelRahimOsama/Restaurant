import {Component, OnInit} from '@angular/core';
import {CardService} from '../../../services/card.service';
import {Cardorder} from '../../models/cardorder';
import {Router} from '@angular/router';
import {OrderService} from '../../../services/order.service';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
class CardDetailsComponent implements OnInit {

  orders: Cardorder[] = [];

  totalPrice: number = 0;
  totalSize: number = 0;

  showModal = false;
  showSuccess = false;
  tableNumber!: number;

  constructor(private cardservice: CardService,private orderService: OrderService) {}

  ngOnInit(): void {

    this.orders = this.cardservice.orders;

    this.cardservice.totalPrice.subscribe(value => {
      this.totalPrice = value;
    });

    this.cardservice.totalSize.subscribe(value => {
      this.totalSize = value;
    });
  }

  plus(order: Cardorder) {
    this.cardservice.changtoplus(order);
  }

  minus(order: Cardorder) {
    this.cardservice.changToMinus(order);
  }

  doneOrder() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  confirmTable() {
    const customerId = Number(sessionStorage.getItem("id"));

    this.orderService.addOrders(
      this.orders,
      customerId,
      this.totalSize,
      this.totalPrice,
      this.tableNumber
    ).subscribe({
      next: (res) => {
        console.log("Order sent", res);

        this.cardservice.doneOrder();
        this.showModal = false;
        this.showSuccess = true;

        setTimeout(() => {
          this.showSuccess = false;
        }, 2000);
      },
      error: (err) => {
        console.error("Order failed", err);
      }
    });
  }
  removeOreder(order){
    this.cardservice.removeOreder(order);
  }

}

export default CardDetailsComponent;

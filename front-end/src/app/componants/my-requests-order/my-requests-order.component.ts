import { Component, OnInit } from '@angular/core';
import {OrderService} from '../../../services/order.service';
import {DataOrder} from '../../models/vm/dataOrder';
import {Router} from '@angular/router';

@Component({
  selector: 'app-my-requests-order',
  templateUrl: './my-requests-order.component.html',
  styleUrls: ['./my-requests-order.component.css']
})
export class MyRequestsOrderComponent implements OnInit {
  showOrderBy=false;
  page = 1;
  pageLength=2;
  orderSize:number;
  orders: DataOrder[]=[];
  constructor(private orderService: OrderService,private router: Router) { }

  ngOnInit(): void {
    this.getOrders(this.page);
  }
  getOrders(page){
    if(this.router.url.includes('/requestMyOrders')){
      this.getmyorders(page);
    }else{
      this.getAllOrders(page);
    }
  }
  getmyorders(page:number){
    this.showOrderBy=false
    this.orderService.getAllMyOrders(page,this.pageLength).subscribe(
      response => {
        this.orders = response.orders;
        this.orderSize=response.totalorders;
      },error =>{
        this.orders = []
      }
    )
  }

  getAllOrders(page:number){
    this.showOrderBy=true
    this.orderService.getAllOrders(page,this.pageLength).subscribe(
      response => {
        this.orders = response.orders;
        this.orderSize=response.totalorders;
      },error =>{
        this.orders = []
      }
    )
  }

  doPagination() {
    this.getOrders(this.page)
  }

  changePageSize(event : Event) {
    this.pageLength = +(<HTMLInputElement>event.target).value;
    this.page = 1;
    this.getOrders(this.page)
  }


}

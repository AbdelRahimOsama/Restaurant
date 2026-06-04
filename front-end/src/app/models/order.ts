import {Cardorder} from './cardorder';

export class Order {
  id: number;
  tableNumber: number;
  totalPrice : number;
  orderItemDtos : Cardorder[];
  totalNumber : number;
  customerId : number;

  constructor(tableNumber: number, totalPrice: number,
  orderItemDtos:Cardorder[],totalNumber : number,
              customerId : number,id?: number) {

  }
}

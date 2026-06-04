import {Product} from '../product';
import {RequistOrder} from './requistOrder';

export class DataOrder {
  id: number;
  orderItems: RequistOrder[];
  code: string;
  tableNumber: number;
  totalPrice: number;
  atdate: Date;
  totalNumber: number;
  customerId: number;
  customerName: string;
}

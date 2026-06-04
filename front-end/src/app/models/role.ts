import {Customer} from './customer';

export class Role {
  private id: number;
  private roleName:string;
  private customer:Customer;
  constructor(id:number,roleName:string,customer:Customer) {
    this.id = id;
    this.roleName = roleName;
    this.customer = customer;
  }
}

import {Customer} from './customer';

export class ContactInfo {
  id?: number;
  name: string;
  email: string;
  subject?: string;
  message: string;
  customerDto : Customer;
  constructor(
    name: string,
    email: string,
    subject: string,
    message: string,
    customer: Customer,
    id?: number,
  ) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.subject = subject;
    this.message = message;
    this.customerDto = customer;
  }
}

import {Role} from './role';
import {ContactInfo} from './contatct-info';

export class Customer {
  id?: number;
  username: string;
  password: string;
  roles: Role[];
  ContactInfo:ContactInfo;
  constructor(
    id?: number,
    username?: string,
    password?: string,
    roles?: Role[],
    ContactInfo?: ContactInfo
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.ContactInfo = ContactInfo;
  }
}

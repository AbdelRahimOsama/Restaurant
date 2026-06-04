export class CustomerDetails {
  id: number;
  name: string;
  email: string;
  phone: string;
  address: string;
  age: number;
  imagePath: string;
  constructor(id?:number, name?: string, email?: string, phone?: string,age?:number, address?: string,imagePath?: string ) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.age = age;
    this.imagePath = imagePath;
  }
}

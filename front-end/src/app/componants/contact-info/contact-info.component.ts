import { Component, OnInit } from '@angular/core';
import {ContactInfo} from '../../models/contatct-info';
import {ContactInfoService} from '../../../services/contact-info.service';
import {Router} from '@angular/router';
import {Customer} from '../../models/customer';

@Component({
  selector: 'app-contact-info',
  templateUrl: './contact-info.component.html',
  styleUrls: ['./contact-info.component.css']
})
class ContactInfoComponent implements OnInit {

  nameInvalid: boolean = false;
  emailInvalid: boolean = false;
  messageInvalid: boolean = false;
  showAcc : boolean = false;
  massage_en:string ='';
  massage_ar:string ='';

  constructor(private contactInfoService : ContactInfoService,private router: Router) { }

  ngOnInit(): void {
  }

  careateContact(name:any,email:any,subject:any,message:any){
    if(!this.validateInputs(name.value,email.value,subject.value,message.value)){
      return;
    }
    const id = sessionStorage.getItem("id");
    const customer = new Customer(Number(id));
    const contactInfo = new ContactInfo(name.value,email.value,subject.value,message.value,customer);

    this.contactInfoService.saveContactInfo(contactInfo).subscribe({
      next: (res) => {
        this.doneSend(name, email, subject, message)
      },
      error: (err) => {
        this.massage_ar = err.error.message_ar;
        this.massage_en = err.error.message_en;
      }
    });
  }

  clear(){
    this.nameInvalid= false;
    this.emailInvalid= false;
    this.messageInvalid = false;
    this.massage_en='';
    this.massage_ar='';
    //this.ngOnInit();
  }

  doneSend(name:any,email:any,subject:any,message:any) {
    this.showAcc = true;
    if (name) name.value = '';
    if (email) email.value = '';
    if (subject) subject.value = '';
    if (message) message.value = '';
    setTimeout(() => {
      this.showAcc = false;
    }, 3000);
  }

  validateInputs(name: string, email: string, subject: string, message: string): boolean {

    this.nameInvalid = !name;
    this.emailInvalid = !email;
    this.messageInvalid = !message;

    return !(this.nameInvalid || this.emailInvalid || this.messageInvalid);
  }
}

export default ContactInfoComponent;

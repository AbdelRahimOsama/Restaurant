import {Component, OnInit} from '@angular/core';
import {CardService} from '../../../services/card.service';
import {Cardorder} from '../../models/cardorder';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})

export class CardComponent implements OnInit {

  totalRice ;
  totalSize ;

  constructor(private cardservice : CardService) {
  }

  ngOnInit(): void {
        this.getCardOrderDetails()
  }

  getCardOrderDetails(){
    this.cardservice.totalPrice.subscribe(
      value => this.totalRice = value,
    );
    this.cardservice.totalSize.subscribe(
      value => this.totalSize = value,
    );
  }
}

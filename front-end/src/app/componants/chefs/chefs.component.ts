import { Component, OnInit } from '@angular/core';
import {ChefService} from '../../../services/chef.service';
import {Chef} from '../../models/chef';

@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrls: ['./chefs.component.css']
})
export class ChefsComponent implements OnInit {

  chefs: Chef[];

  constructor(private chefService: ChefService) { }

  ngOnInit(): void {
    this.getallchefs();
  }
  getallchefs(): void{
    this.chefService.getchefs().subscribe(
      res => this.chefs = res
    );
  }
}

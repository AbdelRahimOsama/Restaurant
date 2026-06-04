import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../services/category.service';
import {Category} from '../../models/category';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit{

  categories: Category[] = [];

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.getAllCategories();
  }

  getAllCategories(): void {
    this.categoryService.getallcategory().subscribe({
      next: (res) => {
        this.categories = res || [];
      },
      error: (err) => {
        console.log(err);
        this.categories = [];
      }
    });
  }

}

import { Component, OnInit } from '@angular/core';
import {Product} from '../../models/product';
import {CategoryService} from '../../../services/category.service';
import {Category} from '../../models/category';
import {ProductService} from '../../../services/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  textNoDescription:string='No description';
  categories : Category[]= [];
  imagePath : string;
  isCategoryValid:boolean = false;
  isImageValid:boolean = false;
  isPriceValid:boolean = false;
  isDescriptionValid:boolean = false;
  isnameValid:boolean = false;

  constructor(private categoryService: CategoryService,private productService: ProductService) { }

  ngOnInit(): void {
    this.categoryService.getallcategory().subscribe(
      data => {
        this.categories = data;
      }
    )
  }

  selectedFile!: File;

  onFileSelected(event: any) {

    const file = event.target.files[0];

    if (file) {

      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePath = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  onSave(name:any,description:any,price:any,categoryName:any,image:any){
    if(!this.validateInputs(name.value,description.value,price.value,categoryName.value)){
      return;
    }
    const formData = new FormData();
    formData.append('name', name.value);
    formData.append('description', description.value);
    formData.append('price', price.value);
    formData.append('categoryName', categoryName.value);
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }
    this.productService.addProduct(formData).subscribe(res => {
      if (name) name.value = '';
      if (description) description.value = '';
      if (price) price.value = null;
      if (categoryName) categoryName.value = 'Select category';
      if(image) image.value = null;
    });

  }
  cleardescription(){
    this.isDescriptionValid= false;

  }clearprice(){
    this.isPriceValid= false;
  }clearimage(){
    this.isImageValid= false;
  }clearname(){
    this.isnameValid= false;
  }clearcategory(){
    this.isCategoryValid= false;
  }

  validateInputs(name: string, description: string, price: number, categoryName: string): boolean {

    this.isnameValid = !name || name.trim().length === 0;

    this.isDescriptionValid = !description || description.trim().length === 0;

    this.isPriceValid = !price || price <= 0;

    this.isImageValid = !this.selectedFile;

    this.isCategoryValid =
      !categoryName ||
      categoryName === 'Select category';

    return !(
      this.isnameValid ||
      this.isDescriptionValid ||
      this.isPriceValid ||
      this.isImageValid ||
      this.isCategoryValid
    );
  }

}

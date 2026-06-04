import {Component, OnInit} from '@angular/core';
import {Product} from '../../models/product';
import {ProductService} from '../../../services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../../services/Security/auth.service';
import {CardService} from '../../../services/card.service';
import {Cardorder} from '../../models/cardorder';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
class ProductsComponent implements OnInit {
  productEdit: Product;
  showModal = false;
  page = 1;
  pageLength=10;
  orderSize:number;
  products: Product[]=[];
  massage_ar : string='';
  massage_en : string='';
  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute
              ,private authService: AuthService,private cardservice: CardService,private router: Router) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(() => {
      this.page = 1;
      this.getproducts(this.page);
    });
  }
  isUserAdmin(){

    return this.authService.isUserAdmin();
  }
  getproducts(page){
    let idExist = this.activatedRoute.snapshot.paramMap.has('id');
    let keyExist = this.activatedRoute.snapshot.paramMap.has('key');
    if(keyExist){
      let key = this.activatedRoute.snapshot.paramMap.get('key');
      this.search(key,page)
    }
    else if(idExist){
      let categoryId = this.activatedRoute.snapshot.paramMap.get('id');
      this.getProductsByCategoryId(categoryId,page);
    }else{
      this.getAllProducts(page)
    }
  }

  getAllProducts(page) {
    this.productService.getallproducts(
      page,this.pageLength).subscribe(
        res => {
          this.products = res.products;
          this.orderSize = res.totalproducts;
          this.massage_en='';
          this.massage_ar='';
        },errorResponse => {
          this.products =[]
        this.massage_en=errorResponse.error.message_en;
        this.massage_ar=errorResponse.error.message_ar;
      }
      );
  }

  private search(key,page){
    this.productService.search(key,page,this.pageLength).subscribe(
      res => {
        this.products = res.products;
        this.orderSize = res.totalproducts;
        this.massage_en='';
        this.massage_ar='';
      },errorResponse => {
        this.products =[]
        this.massage_en=errorResponse.error.message_en;
        this.massage_ar=errorResponse.error.message_ar;
      }
    );
  }
  getProductsByCategoryId(categoryId: string,page) {
    this.productService.getProductsByCategoryId(
      categoryId,page,this.pageLength).subscribe(
      res => {
        this.products = res.products;
        this.orderSize = res.totalproducts;
        this.massage_en='';
        this.massage_ar='';
      },errorResponse => {
        this.products =[]
        this.massage_en=errorResponse.error.message_en;
        this.massage_ar=errorResponse.error.message_ar;
      }
    );
  }

  doPagination() {
    this.getproducts(this.page)
  }

  changePageSize(event : Event) {
    this.pageLength = +(<HTMLInputElement>event.target).value;
    this.page = 1;
    this.getproducts(this.page)
  }

  addproducttoorder(product: Product){
    this.cardservice.addProductToOrder(new Cardorder(product));
  }
  editProduct(prod : Product){
    this.productEdit = prod;
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  selectedFile!: File;
  onFileSelected(event: any) {

    const file = event.target.files[0];

    if (file) {

      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => {
        this.productEdit.imagePath = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  updateProduct(name,description,price) {

    const formData = new FormData();
    const id = this.productEdit.id
    formData.append('id', id.toString());
    formData.append('name', name);
    formData.append('description', description);
    formData.append('price', price);
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }
    this.productService.updateProducr(formData).subscribe(res => {

    });
    this.showModal = false;
    this.router.navigateByUrl('/products');
  }
  deleteProduct(id){
    this.productService.deleteProduct(id).subscribe(res => {
      this.getproducts(this.page)
    })
  }
}

export default ProductsComponent;

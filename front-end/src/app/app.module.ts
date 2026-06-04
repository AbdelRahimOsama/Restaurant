import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {NgModule} from '@angular/core';
import ProductsComponent from './componants/products/products.component';
import {HeaderComponent} from './componants/header/header.component';
import {CategoryComponent} from './componants/category/category.component';
import CardDetailsComponent from './componants/card-details/card-details.component';
import {CardComponent} from './componants/card/card.component';
import {BrowserModule} from '@angular/platform-browser';
import {FooterComponent} from './componants/footer/footer.component';
import { ChefsComponent } from './componants/chefs/chefs.component';
import ContactInfoComponent from './componants/contact-info/contact-info.component';
import {APP_BASE_HREF} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './componants/login/login.component';
import { SignupComponent } from './componants/signup/signup.component';
import {FormsModule} from '@angular/forms';
import {AuthInterceptor} from '../services/Interceptor/auth.interceptor';
import {AuthGuard} from '../services/guard/auth.guard';
import {LoginSignupGuard} from '../services/guard/login-signup.guard';
import { DoneOrderComponent } from './componants/done-order/done-order.component';
import { MyRequestsOrderComponent } from './componants/my-requests-order/my-requests-order.component';
import { ProductDetailsComponent } from './componants/product-details/product-details.component';
import { ProfileComponent } from './componants/profile/profile.component';
import { UpdateProfileComponent } from './componants/update-profile/update-profile.component';
import { AddProductComponent } from './componants/add-product/add-product.component';

// http://localhost:4200/
export const routes: Routes = [

  // http://localhost:4200/active
  {path: 'products', component: ProductsComponent,canActivate: [AuthGuard]},
  {path: 'cardDetails', component: CardDetailsComponent,canActivate: [AuthGuard]},
  {path: 'contact-info', component: ContactInfoComponent,canActivate: [AuthGuard]},
  {path: 'chefs', component: ChefsComponent,canActivate: [AuthGuard]},
  {path : 'category/:id', component: ProductsComponent,canActivate: [AuthGuard]},
  {path : 'search/:key', component: ProductsComponent,canActivate: [AuthGuard]}, // http://localhost:4200/
  {path : 'doneOrder', component: DoneOrderComponent,canActivate:[AuthGuard]},
  {path : 'login', component: LoginComponent,canActivate:[LoginSignupGuard]},
  {path : 'signup', component: SignupComponent,canActivate:[LoginSignupGuard]},
  {path : 'requestMyOrders',component: MyRequestsOrderComponent,canActivate:[AuthGuard]},
  {path : 'requestAllOrders',component: MyRequestsOrderComponent,canActivate:[AuthGuard]},
  {path : 'profile',component: ProfileComponent , canActivate:[AuthGuard]},
  {path : 'profile/update',component: UpdateProfileComponent , canActivate:[AuthGuard]},
  {path : 'product/add',component: AddProductComponent , canActivate:[AuthGuard]},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  // if user enter thing without all routes
  {path: '**', redirectTo: '/login', pathMatch: 'full'}
];

/*
*   // http://localhost:4200/
  {path: '', component:OrderItemsComponent}
* */
@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HeaderComponent,
    CategoryComponent,
    CardDetailsComponent,
    CardComponent,
    FooterComponent,
    ChefsComponent,
    ContactInfoComponent,
    LoginComponent,
    SignupComponent,
    DoneOrderComponent,
    MyRequestsOrderComponent,
    ProductDetailsComponent,
    ProfileComponent,
    UpdateProfileComponent,
    AddProductComponent
  ],
    imports: [
        RouterModule.forRoot(routes),
        BrowserModule,
        HttpClientModule,
        NgbPaginationModule,
        FormsModule
    ],
  providers: [{ provide: APP_BASE_HREF, useValue: '/' },{provide : HTTP_INTERCEPTORS, useClass: AuthInterceptor,multi: true}],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }

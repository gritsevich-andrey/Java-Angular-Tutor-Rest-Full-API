import {Injectable} from '@angular/core';
import {Program} from '../../../shared/interfaces';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  cartProducts: Program[] | any;

  constructor() {
  }

  addToCart(product: never): void {
    this.cartProducts.push(product);
  }

  getItems(): never[] {
    return this.cartProducts;
  }

  clearCart(): never[] {
    this.cartProducts = [];
    return this.cartProducts;
  }
}

import {Component, OnInit} from '@angular/core';
import {ProgramService} from '../program-page/shared/services/program-service';
import {Program} from '../../shared/interfaces';
import {UserService} from '../../services/user.service';
import {NotificationService} from '../../services/notification.service';

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent implements OnInit {
  cartProducts: Program[] = [];
  totalPrice = 0;
  customerId?: string;

  constructor(private programService: ProgramService,
              private userService: UserService,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.cartProducts = this.programService.cartProducts;
    for (let i = 0; i < this.cartProducts.length; i++) {
      this.totalPrice += +this.cartProducts[i].cost;
    }
  }

  delete(product: Program): void {
    this.totalPrice -= +product.cost;
    this.cartProducts.splice(this.cartProducts.indexOf(product), 1);
  }

  buyProgram(product: Program): void {
    this.userService.getCurrentUser()
      .subscribe(user => {
        this.customerId = user.userId;
        this.programService.buyProgram(user.userId, product)
          .subscribe(() => {
              this.notificationService.showSnackBar('Программа куплена');
            }, error => {
              this.notificationService.showSnackBar(error.message = 'Ошибка покупки программы');
            }
          );
      });
  }
}


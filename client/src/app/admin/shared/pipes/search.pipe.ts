import {Pipe, PipeTransform} from '@angular/core';
import {Users} from '../../../shared/interfaces';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(users: Users[], userEmail = ''): any {
    if (!userEmail.trim()) {
      return users;
    }
    return users.filter(user => {
      return user.email.toLowerCase().includes(userEmail.toLowerCase());
    });
  }

}

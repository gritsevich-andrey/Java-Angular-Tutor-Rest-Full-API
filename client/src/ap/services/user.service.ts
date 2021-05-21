import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {Users} from '../shared/interfaces';
import {environment} from '../../environments/environment';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  getUserById(id: string): Observable<any> {
    return this.http.get(environment.USER_API + '/' + id);
  }

  getCurrentUser(): Observable<any> {
    let id = this.tokenStorage.getUser().userId;
    return this.http.get(environment.USER_API + '/' + id);
  }

  updateUser(user: Users): Subscription {
    return this.http.put(environment.USER_API + '/' + user.userId, {userId: user.userId, roles: [user.roles]})
      .subscribe(() => {
        console.log('User role is updated');
      });
  }

  updateUserProfile(user: Users): Subscription {
    return this.http.put(environment.USER_API + '/profile/' + user.userId, {userId: user.userId, profile: user.profile})
      .subscribe(() => {
        console.log('User profile is updated');
      });
  }
}

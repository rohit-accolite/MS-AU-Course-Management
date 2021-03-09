import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class SaveUserService {
  SAVE_USER = '/user/save';
  constructor(private http: HttpClient) { }

  add(user: User){
    console.log('in add serv');
    return this.http.post(this.SAVE_USER, user);
  }
}

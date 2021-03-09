import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { SaveUserService } from './save-user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  constructor(ngZone: NgZone, private router: Router, private saveUserService: SaveUserService) {
    // window.location.reload();
    window['onSignIn'] = (user: any) => ngZone.run( () => {
      this.afterSignIn(user);
    });
  }
  
  ngOnInit(): void {
    
  }

  afterSignIn(googleUser: any) {
    var profile = googleUser.getBasicProfile();
    // console.log('ID: ' + profile.getId());
    // console.log('Name: ' + profile.getName());
    // console.log('Image URL: ' + profile.getImageUrl());
    // console.log('Email: ' + profile.getEmail());
    let name = profile.getName().split(' ', 2);
    // console.log(name);
    this.saveUserService.add(new User(name[0], name[1], profile.getEmail(), new Date())).subscribe(resp => {
      localStorage.setItem('userData', JSON.stringify(resp));
      this.router.navigate(['/home']);
    },
    (error:any)=>{
      console.log('database connection error');
    });
    
  }

  // signOut(): void {
  //   var auth2 = gapi.auth2.getAuthInstance();
  //   auth2.signOut().then(function () {
  //     console.log('User signed out.');
  //   });
  // }
}

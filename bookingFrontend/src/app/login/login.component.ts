import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import {User} from '../user';
import {UserService} from '../user.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = new User;
  error = false;

  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router) { }

  ngOnInit() {
  }

  login(): void {
    this.userService.login(this.user).then(function(user) {
      if (user.role !== 'user' && user.confirmationToken === null) {
        localStorage.setItem('UAuser', JSON.stringify(user));
        window.location.href = 'http://localhost:4200/adminConfirmation';
      } else {
        localStorage.setItem('user', JSON.stringify(user));
        window.location.href = 'http://localhost:4200/profile';
      }      
      })
    .catch(() => this.errorFunc());
  }

  onSubmit() {
    this.login();
  }

  errorFunc(): void {
    this.error = true;
  }

  goBack(): void {
    this.location.back();
  }

}

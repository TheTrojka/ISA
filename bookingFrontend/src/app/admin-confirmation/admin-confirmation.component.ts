import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import {User} from '../user';
import {UserService} from '../user.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-admin-confirmation',
  templateUrl: './admin-confirmation.component.html',
  styleUrls: ['./admin-confirmation.component.css']
})
export class AdminConfirmationComponent implements OnInit {

  user: User;
  submitted = false;
  oldPassword = '';
  checkOld = '';
  checkSame = '';
  errorMessage = '';

  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router) { }

  ngOnInit() {
    if (localStorage.getItem('UAuser')) {
      this.user = JSON.parse(localStorage.getItem('UAuser'));
    } else {
      this.user = JSON.parse(localStorage.getItem('user'));
    }
    this.oldPassword = this.user.password;
    console.log(this.oldPassword);
    this.user.password = '';
  }

  changePassword(): void {
    this.userService.checkPassword(this.checkOld, this.user.id).then(() => {
    if (this.user.password === this.checkSame && this.user.password !== this.checkOld ) {
      localStorage.setItem('user', JSON.stringify(this.user));   
      this.userService.changePassword(this.user).then(() => this.goBack());
    } else if (this.user.password !== this.checkSame) {
      this.errorMessage = 'New passwords are not the same';
    } else if (this.checkOld === this.checkSame) {
      this.errorMessage = 'Password not changed';
    }})
    .catch(() =>  {
      this.errorMessage = 'Incorecct old password';
    });   
  }

  onSubmit() {
    this.changePassword();
  }

  goBack(): void {
    window.location.href = 'http://localhost:4200/profile';
  }

}

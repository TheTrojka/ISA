import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {User} from '../user';
import {UserService} from '../user.service';
import {Location} from '@angular/common';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user = new User;
  submitted = false;
  passwordsSame = true;
  repeat = '';
  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private location: Location) {}
 
  ngOnInit() {
  }

  newUser(): void {   
      this.submitted = false;
      this.user = new User();
  }
 
  private save(): void {
    this.user.role = 'user';
    this.userService.register(this.user)
    .then(() => this.submitted = true)
    .catch(() => alert('User already exists'));
  }
 
  onSubmit() {
    if (this.user.password === this.repeat) {
      this.passwordsSame = true;
      this.save();
    } else {
      this.passwordsSame = false;
    }
  }
 
  goBack(): void {
    this.location.back();
  }

}

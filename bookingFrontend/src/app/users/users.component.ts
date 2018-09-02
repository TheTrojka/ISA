import { Component, OnInit } from '@angular/core';
import { Establishment } from '../establishment';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';
import { User } from '../user';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  prsers: User[];
  users: User[];
  usersCopy: User[];
  friends: User[];
  searchString = '';

  constructor(private userService: UserService,
    private route: ActivatedRoute) {
  }

  getUsers() {
    this.userService.getAvailable(JSON.parse(localStorage.getItem('user'))['id'])
    .then(users => this.users = users)
      .then(() => this.users = this.users
      .filter((el) => !(el.id === JSON.parse(localStorage.getItem('user'))['id'])));
  }

  getUsersCopy() {
    this.userService.getAvailable(JSON.parse(localStorage.getItem('user'))['id'])
    .then(users => this.usersCopy = users)
      .then(() => this.usersCopy = this.usersCopy
      .filter((el) => !(el.id === JSON.parse(localStorage.getItem('user'))['id'])));
  }

  ngOnInit(): void {
    this.getUsers();
    this.getUsersCopy();
  }

  search(): void {
    if (this.searchString !== '') {
      this.users = this.usersCopy.filter(item => (item.name.toLocaleLowerCase()
        .indexOf(this.searchString.toLocaleLowerCase()) >= 0) ||
        (item.lastname.toLocaleLowerCase()
        .indexOf(this.searchString.toLocaleLowerCase()) >= 0));
    }
  }

  reset(): void {
    this.getUsers();
  }

  addFriend(id): void {
    this.userService.addFriend(JSON.parse(localStorage.getItem('user'))['id'], id)
      .then(() => this.goBack());
  }

  goBack(): void {
    window.location.replace('');
  }


}










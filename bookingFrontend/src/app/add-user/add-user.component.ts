import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { User } from '../user';
import { UserService } from '../user.service';
import { DataService } from '../data.service';
import { Establishment } from '../establishment';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  profile = new User();
  OGestablishments: Establishment[];
  establishments: Establishment[] = [];
  error = false;

  constructor(private route: ActivatedRoute,
    private userService: UserService,
    private dataService: DataService,
    private location: Location) {}

    ngOnInit(): void {
      this.getEstablishments();
    }

  save(): void {
    console.log(JSON.stringify(this.profile));
    if (this.profile.establishment) {
      const i = this.profile.establishment;
      this.profile.establishment = null;
      this.userService.create(this.profile, i)
      .then(() => this.goBack())
      .catch(() => this.errorFunc());
    } else {
      this.profile.establishment = null;
      this.userService.create(this.profile, 0)
      .then(() => this.goBack())
      .catch(() => this.errorFunc());
    }   
  }

  getEstablishments(): void {
    this.dataService.getEstablishments().then(establishments => this.OGestablishments = establishments)
    .then(() => this.OGestablishments.forEach(e => {
      if (e.active) {
        this.establishments.push(e);
      }
    }));
  }

  errorFunc() {
    this.error = true;
  }

  goBack(): void {
    window.location.replace('');
  }
}


import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Status} from '../status';
import {UserService} from '../user.service';
import {Location} from '@angular/common';


@Component({
  selector: 'app-user-ranking',
  templateUrl: './user-ranking.component.html',
  styleUrls: ['./user-ranking.component.css']
})
export class UserRankingComponent implements OnInit {

  status: Status;
  correct = true;
  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private location: Location) {}
 
  ngOnInit() {
    this.getStatus();
  }

  private getStatus(): void {
    this.userService.getStatus()
    .then(status => this.status = status)
    .catch(() => alert('Error'));
  }
 
  private save(): void {
    this.userService.setStatus(this.status)
    .then(() => this.goBack())
    .catch(() => alert('Error'));
  }
 
  onSubmit() {
    if (this.status.silver < this.status.gold) {
      this.correct = true;
      this.save();
    } else {
      this.correct = false;
    }
  }
 
  goBack(): void {
    this.location.back();
  }

}


import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { User } from '../user';
import { UserService } from '../user.service';
import { HappeningService } from '../happening.service';
import { Visit } from '../visit';
import { Timing } from '../timing';
import { DataService } from '../data.service';
import { Status } from '../status';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @Input() profile: User;
  timings: Timing[] = [];
  history: Visit[];
  historyCopy: Visit[];
  friends: User[];
  requests: User[];
  rating = '';
  searchString = '';
  invitee = 0;
  friendTiming: number;
  rateCurr: number;
  user = false;
  status = new Status();

  constructor(private route: ActivatedRoute,
    private userService: UserService,
    private happeningService: HappeningService,
    private dataService: DataService) { }

  ngOnInit(): void {
    this.getProfile();
    if (localStorage.getItem('userRole')) {
      this.user = true;
      this.getStatus();
      this.getHistory();
      this.getHistoryCopy();
      this.getFriends();
      this.getRequests();
    }
  }

  getProfile(): void {
    this.profile = JSON.parse(localStorage.getItem('user'));
  }

  getStatus(): void {
    this.userService.getStatus()
      .then(status => this.status = status)
      .catch(() => alert('Error'));
  }

  getHistory(): void {
    this.happeningService.getUserReservations(JSON.parse(localStorage.getItem('user'))['id'])
      .then(visits => this.history = visits)
      .then(() => this.history.forEach(e => e.multiple = this.hasDuplicates(e, this.history)))
      .then(() => {
        const d = new Date();
        d.setMinutes(d.getMinutes() + 30);
        this.history.forEach(e => e.canCancel = d.valueOf() < Date.parse(this.fixDate(e.date).valueOf()));
      })
      .then(() => {
        const d = new Date();
        d.setHours(d.getHours() - 1);
        this.history.forEach(e => e.canRate = Date.parse(this.fixDate(e.date).valueOf()) < d.valueOf());
      })
      .then(() => {
        const d = new Date();
        d.setMinutes(d.getMinutes() + 30);
        this.history.forEach(e => e.canInvite = d.valueOf() < Date.parse(this.fixDate(e.date).valueOf()));
      });
  }

  inviteFr(id) {
    this.invitee = 0;
    this.friendTiming = id;
  }

  rateOE(id) {
    this.rating = '';
    this.rateCurr = id;
  }

  rateOH(id) {
    this.rating = '';
    this.rateCurr = id;
  }

  changePassword() {
    window.location.href = 'http://localhost:4200/adminConfirmation';
  }


  hasDuplicates(visit, hisarray): boolean {
    let count = 0;
    hisarray.forEach(e => {
      if (e.name === visit.name && e.date === visit.date
        && e.establishment === visit.establishment) { count = count + 1; }
    });
    if (count > 1) {
      return true;
    } else {
      return false;
    }
  }

  getHistoryCopy(): void {
    this.happeningService.getUserReservations(JSON.parse(localStorage.getItem('user'))['id'])
      .then(visits => this.historyCopy = visits)
      .then(() => this.historyCopy.forEach(e => e.multiple = this.hasDuplicates(e, this.historyCopy)))
      .then(() => {
        const d = new Date();
        d.setMinutes(d.getMinutes() + 30);
        this.historyCopy.forEach(e => e.canCancel = d.valueOf() < Date.parse(this.fixDate(e.date).valueOf()));
      })
      .then(() => {
        const d = new Date();
        d.setHours(d.getHours() - 1);
        this.historyCopy.forEach(e => e.canRate = Date.parse(this.fixDate(e.date).valueOf()) < d.valueOf());
      })
      .then(() => {
        const d = new Date();
        d.setMinutes(d.getMinutes() + 30);
        this.history.forEach(e => e.canInvite = d.valueOf() < Date.parse(this.fixDate(e.date).valueOf()));
      });
  }

  getFriends(): void {
    this.userService.getFriends(JSON.parse(localStorage.getItem('user'))['id'])
      .then(friends => this.friends = friends);
  }

  getRequests(): void {
    this.userService.getRequests(JSON.parse(localStorage.getItem('user'))['id'])
      .then(requests => this.requests = requests);
  }

  save(): void {
    console.log(this.profile);
    localStorage.setItem('user', JSON.stringify(this.profile));
    this.userService.update(this.profile).then(() => this.goBack());
  }

  rateEstablishment(tid, eid, rating): void {
    console.log(tid);
    if ((<HTMLInputElement>document.getElementById(tid)).value === this.rating) {
      this.dataService.rateEstablishment(eid, rating, JSON.parse(localStorage.getItem('user'))['id'])
        .then(() => this.goBack());
    }
  }

  rateHappening(tid, hid, rating): void {
    console.log(tid);
    if ((<HTMLInputElement>document.getElementById(tid)).value === this.rating) {
      this.dataService.rateHappening(hid, rating, JSON.parse(localStorage.getItem('user'))['id'])
        .then(() => this.goBack());
    }
  }

  cancel(id): void {
    this.userService.cancelReservation(id)
      .then(() => this.goBack());
  }

  accept(id): void {
    this.userService.acceptFriend(id, JSON.parse(localStorage.getItem('user'))['id'])
      .then(() => this.goBack());
  }

  decline(id): void {
    this.userService.declineFriend(id, JSON.parse(localStorage.getItem('user'))['id'])
      .then(() => this.goBack());
  }

  deleteFriend(id): void {
    this.userService.deleteFriend(id, JSON.parse(localStorage.getItem('user'))['id'])
      .then(() => this.goBack());
  }

  search(): void {
    if (this.searchString !== '') {
      this.history = this.historyCopy.filter(item => item.name.toLocaleLowerCase()
        .indexOf(this.searchString.toLocaleLowerCase()) >= 0 ||
        item.establishment.toLocaleLowerCase()
          .indexOf(this.searchString.toLocaleLowerCase()) >= 0);
    }
  }

  reset(): void {
    this.getHistory();
  }

  invite(id): void {
    console.log('heh');
    if (this.invitee !== 0) {
      this.userService.invite(id, this.invitee)
        .then(() => this.goBack());
    }
  }

  fixDate(date): string {
    console.log(date);
    const separators = [' ', ':', '-'];
    const splitted = date.split(new RegExp(separators.join('|'), 'g'), 6);
    const returnString = splitted[0] + '-' + splitted[1] + '-' + splitted[2] + 'T' + splitted[3] + ':' + splitted[4];
    console.log(returnString);
    return returnString;
  }

  goBack(): void {
    window.location.replace('');
  }
}

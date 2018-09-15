import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Happening } from '../happening';
import { DataService } from '../data.service';
import { HappeningService } from '../happening.service';

@Component({
  selector: 'app-happenings',
  templateUrl: './happenings.component.html',
  styleUrls: ['./happenings.component.css']
})
export class HappeningsComponent implements OnInit {

  happenings: Happening[];
  happeningsCopy: Happening[];
  selectedHappening: Happening;

  admin = false;
  Eadmin = false;
  user = false;
  searchString = '';

  constructor(private happeningService: HappeningService,
    private dataService: DataService,
    private route: ActivatedRoute) { }

  getHappenings() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getHappenings(id).then(happenings => this.happenings = happenings);
  }

  getHappeningsCopy() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getHappenings(id).then(happenings => this.happeningsCopy = happenings);
  }

  ngOnInit(): void {
    this.getHappenings();
    this.getHappeningsCopy();
    if (localStorage.getItem('userRole')) {
      this.user = true;
    } else if (localStorage.getItem('admin')) {
      this.admin = true;
    } else if (localStorage.getItem('Eadmin')) {
      this.checkAdmin();
    }
  }

  checkAdmin() {
    this.dataService.checkIfAdmin(+this.route.snapshot.paramMap.get('establishmentId')
    , JSON.parse(localStorage.getItem('user'))['id']).then(discounts => this.Eadmin = true);
    localStorage.setItem('Eadmin', 'Eadmin');
  }

  onSelect(happ: Happening): void {
    console.log('no');
    this.selectedHappening = happ;
  }

  sortByName(): void {
    this.happenings.sort((obj1, obj2) => {
      if (obj1.title.toLocaleLowerCase() > obj2.title.toLocaleLowerCase()) {
        return 1;
      }

      if (obj1.title.toLocaleLowerCase() < obj2.title.toLocaleLowerCase()) {
        return -1;
      }

      return 0;
    });
  }

  sortByRating(): void {
    this.happenings.sort((obj1, obj2) => {
      if (+obj1.rating < +obj2.rating) {
        return 1;
      }

      if (+obj1.rating > +obj2.rating) {
        return -1;
      }

      return 0;
    });
  }

  search(): void {
    if (this.searchString !== '') {
      this.happenings = this.happeningsCopy.filter(item => item.title.toLocaleLowerCase()
        .indexOf(this.searchString.toLocaleLowerCase()) >= 0);
    }
  }

  reset(): void {
    this.getHappenings();
  }

}
















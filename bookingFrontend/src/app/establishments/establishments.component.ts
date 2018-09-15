import { Component, OnInit } from '@angular/core';
import { Establishment } from '../establishment';
import { DataService } from '../data.service';
import { ActivatedRoute } from '@angular/router';
import { Point } from '../point';
import { User } from '../user';


@Component({
  selector: 'app-establishments',
  templateUrl: './establishments.component.html',
  styleUrls: ['./establishments.component.css']
})
export class EstablishmentsComponent implements OnInit {

  establishments: Establishment[];
  establishmentsCopy: Establishment[];
  selectedEstablishment: Establishment;
  type: string;
  searchString = '';
  distance = false;
  point = new Point();
  variable: any;
  user: string;

  constructor(private dataService: DataService,
    private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.type = params['listType'];
      this.ngOnInit();
    });
  }

  getEstablishments() {
    this.dataService.getEstablishments()
      .then(establishments => {
        this.establishments = establishments;
        this.establishments.forEach(e => {
          if (localStorage.getItem('user')) {
            this.dataService.showAddress(encodeURI(e.address))
              .then(object => {
                e.point = object.results[0].geometry.location;
                console.log(Object.keys(e));
                const distanceNumber = this.getDistance(e.point.lat, e.point.lng,
                  this.point.lat, this.point.lng) / 1000;
                e.distance = distanceNumber.toFixed(1);
              });
          }
        });
      });
  }

  getUserPoint() {
    if (localStorage.getItem('user')) {
      this.user = JSON.stringify(localStorage.getItem('user'));
      this.dataService.showAddress(encodeURI(JSON.parse(localStorage.getItem('user'))['city']))
        .then(object => {
          this.point.lat = object.results[0].geometry.location.lat;
          this.point.lng = object.results[0].geometry.location.lng;
        })
        .then(() => {
          this.getEstablishments();
        });
    } else {
      this.getEstablishments();
    }


  }

  getEstablishmentsCopy() {
    this.dataService.getEstablishments().then(establishments => this.establishmentsCopy = establishments);
    /*.then(() => {
      if (this.distance) {
        this.establishments.forEach(e => e.distance = this.getDistance(this.getlatlng(e.address), 
        this.getlatlng(JSON.parse(localStorage.getItem('user'))['city'])));
      }
    });*/
  }

  ngOnInit(): void {
    this.type = this.route.snapshot.paramMap.get('listType');
    //  console.log(this.distance);
    this.getUserPoint();
    this.getEstablishmentsCopy();

  }

  sortByName(): void {
    this.establishments.sort((obj1, obj2) => {
      if (obj1.name.toLocaleLowerCase() > obj2.name.toLocaleLowerCase()) {
        return 1;
      }

      if (obj1.name.toLocaleLowerCase() < obj2.name.toLocaleLowerCase()) {
        return -1;
      }

      return 0;
    });
  }

  sortByRating(): void {
    this.establishments.sort((obj1, obj2) => {
      if (+obj1.rating < +obj2.rating) {
        return 1;
      }

      if (+obj1.rating > +obj2.rating) {
        return -1;
      }

      return 0;
    });
  }

  sortByDistance(): void {
    this.establishments.sort((obj1, obj2) => {
      if (+obj1.distance > +obj2.distance) {
        return 1;
      }

      if (+obj1.distance < +obj2.distance) {
        return -1;
      }

      return 0;
    });
  }

  search(): void {
    if (this.searchString !== '') {
      this.establishments = this.establishmentsCopy.filter(item => item.name.toLocaleLowerCase()
        .indexOf(this.searchString.toLocaleLowerCase()) >= 0);
    }
  }

  reset(): void {
    this.getEstablishments();
  }

  onSelect(est: Establishment): void {
    this.selectedEstablishment = est;
  }

  rad(x): number {
    return x * Math.PI / 180;
  }

  getDistance(p1lat, p1lng, p2lat, p2lng): number {
    console.log(p1lat);
    console.log(p1lng);
    console.log(p2lat);
    console.log(p2lng);
    const R = 6378137; // Earth’s mean radius in meter
    const dLat = this.rad(p2lat - p1lat);
    const dLong = this.rad(p2lng - p1lng);
    const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(this.rad(p1lat)) * Math.cos(this.rad(p2lat)) *
      Math.sin(dLong / 2) * Math.sin(dLong / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const d = R * c;
    return d; // returns the distance in meter
  }

}










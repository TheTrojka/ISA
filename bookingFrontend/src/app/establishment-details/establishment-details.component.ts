import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Establishment } from '../establishment';
import { Discount } from '../discount';
import { DataService } from '../data.service';


@Component({
  selector: 'app-establishment-details',
  templateUrl: './establishment-details.component.html',
  styleUrls: ['./establishment-details.component.css'],
  providers: [DataService]
})
export class EstablishmentDetailsComponent implements OnInit {

  @Input() establishment: Establishment;
  discounts: Discount[];
  admin = false;
  Eadmin = false;
  user = false;
  lat = 51.678418;
  lng = 7.809007;

  constructor(private route: ActivatedRoute,
    private dataService: DataService,
    private location: Location) { }

  ngOnInit(): void {
    this.getEstablishment();
    this.getDiscounts();
    if (localStorage.getItem('userRole')) {
      this.user = true;
    } else if (localStorage.getItem('admin')) {
      this.admin = true;
    } else if (localStorage.getItem('Eadmin')) {
      this.checkAdmin();
    }
  }

  getEstablishment(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.dataService.getEstablishment(id)
      .then(establishment => this.establishment = establishment)
      .then(() => this.getlatlng(encodeURI(this.establishment.address)));
  }

  getDiscounts(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.dataService.getDiscounts(id).then(discounts => this.discounts = discounts);
  }

  takeDiscount(discountId): void {
    this.dataService.takeDiscount(discountId, JSON.parse(localStorage.getItem('user'))['id'])
      .then(() => this.goBack())
      .catch(() => alert('Discount already taken please refresh browser'));
  }

  checkAdmin() {
    this.dataService.checkIfAdmin(+this.route.snapshot.paramMap.get('establishmentId')
      , JSON.parse(localStorage.getItem('user'))['id']).then(discounts => this.Eadmin = true);
    localStorage.setItem('Eadmin', 'Eadmin');
  }

  checkTime(date) {
    date.slice(0, -5);
    date.replace(' ', 'T');
    if (Date.parse(date) < Date.now()) {
      return false;
    } else {
      return true;
    }
  }

  delete(): void {
    this.dataService.delete(this.establishment.id).then(() => this.goBack());
  }

  save(): void {
    this.dataService.update(this.establishment).then(() => this.goBack())
    .catch(() => alert('Establishment already exists'));
  }

  goBack(): void {
    window.location.replace('');
  }

  // Initialize and add the map
  getlatlng(address) {
    console.log('fsdf');
    this.dataService.showAddress(address).then(object => {
       this.lat = object.results[0].geometry.location.lat;
       this.lng = object.results[0].geometry.location.lng;
    });
  }

}





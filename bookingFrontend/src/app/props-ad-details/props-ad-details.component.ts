import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location, DatePipe } from '@angular/common';
import { PropsAd } from '../props-ad';
import { PropsAdService } from '../props-ad.service';
import { Offer } from '../offer';

@Component({
  selector: 'app-props-ad-details',
  templateUrl: './props-ad-details.component.html',
  styleUrls: ['./props-ad-details.component.css'],
  providers: [PropsAdService]
})
export class PropsAdDetailsComponent implements OnInit {

  @Input() propsAd: PropsAd;
  offers: Offer[];
  ownOffer: Offer;
  initialOffer: string;
  changedOffer: string;
  seller: string;
  expired = false;

  constructor(private route: ActivatedRoute,
    private propsAdService: PropsAdService,
    private location: Location,
    private datepipe: DatePipe) { }

  ngOnInit(): void {
    this.getPropsAd();
    console.log(this.expired);
    this.getOffers();
    this.getOwnOffer();
    this.isSeller();
  }

  getPropsAd(): void {
    const id = +this.route.snapshot.paramMap.get('propsAdId');
    this.propsAdService.getPropAd(id)
      .then(propsAd => this.propsAd = propsAd)
      .then(() => {
        this.expired = Date.parse(this.fixDate(this.propsAd.date)) < Date.now();
        console.log(this.fixDate(this.propsAd.date));
        console.log(Date.now());
        console.log(Date.parse(this.propsAd.date) < Date.now());
      });
  }

  getOffers(): void {
    const id = +this.route.snapshot.paramMap.get('propsAdId');
    this.propsAdService.getOffers(id)
      .then(offers => this.offers = offers)
      .then(() => this.offers.sort((obj1, obj2) => {
        if (+obj1.price < +obj2.price) {
          return 1;
        }

        if (+obj1.price > +obj2.price) {
          return -1;
        }

        return 0;
      }))
      .then(() => this.offers.forEach(e => this.propsAdService.getUser(id, e.id)
      .then(user => e.user = user))
      );
  }

  getOwnOffer(): void {
    const id = +this.route.snapshot.paramMap.get('propsAdId');
    this.propsAdService.getOwnOffer(id, JSON.parse(localStorage.getItem('user'))['id'])
      .then(offer => this.ownOffer = offer);
  }

  makeOffer(): void {
    const id = +this.route.snapshot.paramMap.get('propsAdId');
    this.propsAdService.makeOffer(id, JSON.parse(localStorage.getItem('user'))['id'], this.initialOffer).then(() => this.goBack());
  }

  changeOffer(): void {
    const id = +this.route.snapshot.paramMap.get('propsAdId');
    this.propsAdService.changeOffer(id, JSON.parse(localStorage.getItem('user'))['id'], this.changedOffer).then(() => this.goBack());
  }

  removeOffer(id): void {
    const pid = +this.route.snapshot.paramMap.get('propsAdId');
    this.propsAdService.removeOffer(pid, id).then(() => this.goBack());
  }

  acceptOffer(userId): void {
    console.log('hey');
    const id = +this.route.snapshot.paramMap.get('propsAdId');
    this.propsAdService.acceptOffer(id, userId)
    .then(() => this.goBack());
  }

  delete(): void {
    this.propsAdService.delete(this.propsAd.id).then(() => this.goBack());
  }

  save(): void {

    this.propsAdService.update(this.propsAd).then(() => this.goBack());
  }

  isSeller(): void {
    const id = +this.route.snapshot.paramMap.get('propsAdId');
    this.propsAdService.getSeller(id, JSON.parse(localStorage.getItem('user'))['id'])
    .then(seller => this.seller = seller);
  }

  fixDate(date): string {
    const separators = [' ', '/', ':'];
    const splitted = date.split(new RegExp(separators.join('|'), 'g'), 6);
    const returnString = splitted[2] + '-' + splitted[1] + '-' + splitted[0] + 'T' + splitted[3] + ':' + splitted[4];
    return returnString; 
  }

  goBack(): void {
    window.location.replace('');
  }


}

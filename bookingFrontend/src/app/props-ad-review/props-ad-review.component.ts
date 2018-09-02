import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PropsAd } from '../props-ad';
import { PropsAdService } from '../props-ad.service';
@Component({
  selector: 'app-props-ad-review',
  templateUrl: './props-ad-review.component.html',
  styleUrls: ['./props-ad-review.component.css']
})
export class PropsAdReviewComponent implements OnInit {

  propsAd: PropsAd[];

  constructor(private propsAdService: PropsAdService, private route: ActivatedRoute) { }

  getPropsAd() {    
    this.propsAdService.getPropsAd().then(propsAd => this.propsAd = propsAd)
    .then(() => 
      this.propsAd.forEach( e =>  e.canReview = Date.parse(this.fixDate(e.date)) > Date.now()
    ));
  }

  ngOnInit(): void {
    this.getPropsAd();
  }

  accept(id): void {
    this.propsAdService.accept(id).then(() => this.goBack())
    .catch(() => alert('Item already reviewed please refresh browser'));
  }

  decline(id): void {
    this.propsAdService.delete(id).then(() => this.goBack())
    .catch(() => alert('Item already reviewed please refresh browser'));
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


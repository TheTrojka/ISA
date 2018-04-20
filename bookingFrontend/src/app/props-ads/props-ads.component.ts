import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PropsAd } from '../props-ad';
import { PropsAdService } from '../props-ad.service';
@Component({
  selector: 'app-props-ads',
  templateUrl: './props-ads.component.html',
  styleUrls: ['./props-ads.component.css']
})
export class PropsAdsComponent implements OnInit {

  propsAd: PropsAd[];
  selectedPropsAd: PropsAd;

  constructor(private propsAdService: PropsAdService, private route: ActivatedRoute) { }

  getPropsAd() {
  
    
    this.propsAdService.getPropsAd().then(propsAd => this.propsAd = propsAd);
  }

  ngOnInit(): void {
    this.getPropsAd();
  }
  onSelect(est: PropsAd): void {
    this.selectedPropsAd = est;
  }

}

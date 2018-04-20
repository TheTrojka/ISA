import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { PropsAd } from '../props-ad';
import { PropsAdService } from '../props-ad.service';

@Component({
  selector: 'app-props-ad-details',
  templateUrl: './props-ad-details.component.html',
  styleUrls: ['./props-ad-details.component.css'],
  providers: [PropsAdService]
})
export class PropsAdDetailsComponent {

   @Input() propsAd: PropsAd;
 
  constructor(private route: ActivatedRoute,
    private propsAdService: PropsAdService,
    private location: Location) {}

    ngOnInit(): void {
      this.getPropsAd();
    }

    getPropsAd(): void {
      const id = +this.route.snapshot.paramMap.get('propsAdId');
      this.propsAdService.getPropAd(id)
        .then(propsAd => this.propsAd = propsAd);
    }

  delete(): void {
    this.propsAdService.delete(this.propsAd.id).then(() => this.goBack());
  }

  save(): void {
    
    this.propsAdService.update(this.propsAd).then(() => this.goBack());
  }
 
  goBack(): void {
    window.location.replace('');
  }


}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {PropsAd} from '../props-ad';
import {PropsAdService} from '../props-ad.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-create-props-ad',
  templateUrl: './create-props-ad.component.html',
  styleUrls: ['./create-props-ad.component.css']
})
export class CreatePropsAdComponent implements OnInit {

   propsAd = new PropsAd;
  submitted = false;
  constructor(private propsAdService: PropsAdService,
     private route: ActivatedRoute,
    private location: Location) {}
 
  ngOnInit() {
  }

  newPropAd(): void {
    this.submitted = false;
    this.propsAd = new PropsAd();
  }
 
  private save(): void {
    const id = +this.route.snapshot.paramMap.get('fanzoneId');
    this.propsAdService.create(id, this.propsAd);
  }
 
  onSubmit() {
    this.submitted = true;
    this.save();
  }
 
  goBack(): void {
    this.location.back();
  }

}

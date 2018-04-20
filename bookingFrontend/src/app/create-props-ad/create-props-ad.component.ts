import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {PropsAd} from '../props-ad';
import {PropsAdService} from '../props-ad.service';
import {Location, DatePipe} from '@angular/common';

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
    private location: Location,
    private datepipe: DatePipe) {}
 
  ngOnInit() {
  }

  newPropAd(): void {
    this.submitted = false;
    this.propsAd = new PropsAd();
  }
 
  private save(): void {
    const DateString = this.datepipe.transform(this.propsAd.date, 'dd/MM/yyyy HH:mm:ss');
    const realDate = new Date(DateString);
    console.log(realDate);
    this.propsAd.date = realDate; 
    this.propsAdService.create(this.propsAd);
  }
 
  onSubmit() {
    this.submitted = true;
    this.save();
  }
 
  goBack(): void {
    this.location.back();
  }

}

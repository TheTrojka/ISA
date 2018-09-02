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
  base64textString = [];
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
    console.log(DateString);
    this.propsAd.date = DateString;
    if (this.base64textString[0]) {
      this.propsAd.picture = this.base64textString[0];
    } 
    console.log(JSON.parse(localStorage.getItem('user'))['id']);
    this.propsAdService.create(this.propsAd, JSON.parse(localStorage.getItem('user'))['id']);
  }
 
  onSubmit() {
    this.submitted = true;
    this.save();
  }

onUploadChange(evt: any) {
  const file = evt.target.files[0];

  if (file) {
    const reader = new FileReader();

    reader.onload = this.handleReaderLoaded.bind(this);
    reader.readAsBinaryString(file);
  }
}

handleReaderLoaded(e) {
  this.base64textString.push('data:image/png;base64,' + btoa(e.target.result));
}
 
  goBack(): void {
    this.location.back();
  }

}

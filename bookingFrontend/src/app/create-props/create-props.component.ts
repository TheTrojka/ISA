import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Props} from '../props';
import {PropsService} from '../props.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-create-props',
  templateUrl: './create-props.component.html',
  styleUrls: ['./create-props.component.css']
})
export class CreatePropsComponent implements OnInit {

  props = new Props;
  submitted = false;
  constructor(private propsService: PropsService,
    private route: ActivatedRoute,
    private location: Location) {}
 
  ngOnInit() {
  }

  newProp(): void {
    this.submitted = false;
    this.props = new Props();
  }
 
  private save(): void {
    const id = +this.route.snapshot.paramMap.get('fanzoneId');
    this.propsService.create(id, this.props);
  }
 
  onSubmit() {
    this.submitted = true;
    this.save();
  }
 
  goBack(): void {
    this.location.back();
  }

}

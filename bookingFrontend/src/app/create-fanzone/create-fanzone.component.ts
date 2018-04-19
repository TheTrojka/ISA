import { Component, OnInit } from '@angular/core';
import {Fanzone} from '../fanzone';
import {FanzoneService} from '../fanzone.service';
import {Location} from '@angular/common';
@Component({
  selector: 'app-create-fanzone',
  templateUrl: './create-fanzone.component.html',
  styleUrls: ['./create-fanzone.component.css']
})
export class CreateFanzoneComponent implements OnInit {

  fanzone = new Fanzone;
  submitted = false;
  constructor(private fanzoneService: FanzoneService,
    private location: Location) {}
 
  ngOnInit() {
  }

  newFanZone(): void {
    this.submitted = false;
    this.fanzone = new Fanzone();
  }
 
  private save(): void {
    this.fanzoneService.create(this.fanzone);
  }
 
  onSubmit() {
    this.submitted = true;
    this.save();
  }
 
  goBack(): void {
    this.location.back();
  }

}

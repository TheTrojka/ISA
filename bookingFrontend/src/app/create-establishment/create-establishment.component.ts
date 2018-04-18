import { Component, OnInit } from '@angular/core';
import {Establishment} from '../establishment';
import {DataService} from '../data.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-create-establishment',
  templateUrl: './create-establishment.component.html',
  styleUrls: ['./create-establishment.component.css']
})
export class CreateEstablishmentComponent implements OnInit {

  establishment = new Establishment;
  submitted = false;
  constructor(private dataService: DataService,
    private location: Location) {}

  ngOnInit() {
  }

  newEstablishment(): void {
    this.submitted = false;
    this.establishment = new Establishment();
  }

  private save(): void {
    this.dataService.create(this.establishment);
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  goBack(): void {
    this.location.back();
  }
}





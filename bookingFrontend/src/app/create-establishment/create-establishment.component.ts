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
    console.log(this.establishment);
    if (!this.establishment.hasOwnProperty('theater')) {
      this.establishment.theater = false;
    }
    console.log(this.establishment);
    this.establishment.active = true;
    this.dataService.create(this.establishment)
    .then(() => this.submitted = true)
    .catch(() => alert('Establishment already exists'));
  }
 
  onSubmit() {
    this.save();
  }
 
  goBack(): void {
    this.location.back();
  }
}

 
  
 
  

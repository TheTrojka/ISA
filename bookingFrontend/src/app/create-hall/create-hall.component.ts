import { Component, OnInit } from '@angular/core';
import {Hall} from '../hall';
import {HallService} from '../hall.service';
import {Location} from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-hall',
  templateUrl: './create-hall.component.html',
  styleUrls: ['./create-hall.component.css']
})
export class CreateHallComponent implements OnInit {

  hall = new Hall;
  submitted = false;
  constructor(private hallService: HallService,
    private route: ActivatedRoute,
    private location: Location) {}

  ngOnInit() {
  }

  newHall(): void {
    this.submitted = false;
    this.hall = new Hall();
  }

  private save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.hallService.create(id, this.hall)
    .then(() => this.goBack())
    .catch(() => alert('Name already exists'));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  goBack(): void {
    this.location.back();
  }

}






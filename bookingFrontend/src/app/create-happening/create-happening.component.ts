import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Happening} from '../happening';
import {HappeningService} from '../happening.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-create-happening',
  templateUrl: './create-happening.component.html',
  styleUrls: ['./create-happening.component.css']
})
export class CreateHappeningComponent implements OnInit {

  happening = new Happening;
  submitted = false;
  constructor(private happeningService: HappeningService,
    private route: ActivatedRoute,
    private location: Location) {}

  ngOnInit() {
  }

  newHappening(): void {
    this.submitted = false;
    this.happening = new Happening();
  }

  private save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.create(id, this.happening);
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  goBack(): void {
    this.location.back();
  }

}







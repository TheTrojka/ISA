import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FormControl} from '@angular/forms';
import { Happening } from '../happening';
import { Seat } from '../seat';
import { Segment } from '../segment';
import { HappeningService } from '../happening.service';
import { SegmentService } from '../segment.service';
import { SearchEstablishmentsComponent } from '../search-establishments/search-establishments.component';

@Component({
  selector: 'app-segment-details',
  templateUrl: './segment-details.component.html',
  styleUrls: ['./segment-details.component.css']
})
export class SegmentDetailsComponent {

  @Input() segment: Segment;
  seats: Seat[];

  constructor(
    private route: ActivatedRoute,
    private segmentService: SegmentService) {}

  delete(): void {
    this.segmentService.delete(this.segment.id).then(() => this.goBack());
  }

  save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.update(id, this.segment).then(() => this.goBack());
  }

  goBack(): void {
    window.location.replace('');
  }

  getSeats() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.getSeats(id, this.segment.id).then(seats => this.seats = seats);
    console.log(this.segment.id);
  }

  deleteSeat(seatId: number) {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.deleteSeat(id, this.segment.id, seatId).then(() => this.goBack());
  }

  addSeat() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.addSeat(id, this.segment.id).then(() => this.goBack());
  }

}





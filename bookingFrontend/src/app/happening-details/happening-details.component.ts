import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FormControl} from '@angular/forms';
import { Happening } from '../happening';
import { Segment } from '../segment';
import { HappeningService } from '../happening.service';
import { SegmentService } from '../segment.service';
import { Timing } from '../timing';
import { Seat } from '../seat';

@Component({
  selector: 'app-happening-details',
  templateUrl: './happening-details.component.html',
  styleUrls: ['./happening-details.component.css']
})
export class HappeningDetailsComponent implements OnInit {

  @Input() happening: Happening;
  timingSeatControl = new FormControl();
  segments: Segment[];
  timings: Timing[];
  timingSeats: Seat[];

  constructor(private happeningService: HappeningService,
    private route: ActivatedRoute,
    private segmentService: SegmentService) {}

  delete(): void {
    this.happeningService.delete(this.happening.id).then(() => this.goBack());
  }

  save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.update(id, this.happening).then(() => this.goBack());
  }

  getSegments() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.getSegments(id).then(segments => this.segments = segments);
  }

  getTimings() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getTimings(id, this.happening.id).then(timings => this.timings = timings);
  }

  getTimingSeats(timingId: number) {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getTimingSeats(id, this.happening.id, timingId)
    .then(timingSeats => this.timingSeats = timingSeats);
  }

  book(timingId: number) {
    this.timingSeatControl.value.forEach(element => {
      this.happeningService.book(1, timingId, element);
    });
    this.goBack();
  }

  ngOnInit(): void {
    this.getSegments();
  }

  goBack(): void {
    window.location.replace('');
  }

}




import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Timing} from '../timing';
import {HappeningService} from '../happening.service';
import {SegmentService} from '../segment.service';
import {Location, DatePipe} from '@angular/common';
import { Segment } from '../segment';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-timing',
  templateUrl: './add-timing.component.html',
  styleUrls: ['./add-timing.component.css']
})
export class AddTimingComponent implements OnInit {

  timing = new Timing;
  segmentControl = new FormControl();
  submitted = false;
  segments: Segment[];
  constructor(private happeningService: HappeningService,
    private segmentService: SegmentService,
    private route: ActivatedRoute,
    private location: Location,
    private datepipe: DatePipe) {}

  ngOnInit() {
    this.getSegments();
  }

  newTiming(): void {
    this.submitted = false;
    this.timing = new Timing();
  }

  private save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    const happId = +this.route.snapshot.paramMap.get('happeningId');
    const latest_date = this.datepipe.transform(this.timing.time, 'dd/MM/yyyy HH:mm:ss');
    console.log(this.timing.time);
    console.log(latest_date);
    this.happeningService.addTiming(latest_date, id, happId , this.segmentControl.value);
  }

  getSegments() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.getSegments(id).then(segments => this.segments = segments);
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  goBack(): void {
    this.location.back();
  }

}









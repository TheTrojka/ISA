import { Component, OnInit } from '@angular/core';
import {Segment} from '../segment';
import {SegmentService} from '../segment.service';
import {Location} from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-segment',
  templateUrl: './create-segment.component.html',
  styleUrls: ['./create-segment.component.css']
})
export class CreateSegmentComponent implements OnInit {

  segment = new Segment;
  submitted = false;
  constructor(private segmentService: SegmentService,
    private route: ActivatedRoute,
    private location: Location) {}

  ngOnInit() {
  }

  newSegment(): void {
    this.submitted = false;
    this.segment = new Segment();
  }

  private save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.create(id, this.segment);
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  goBack(): void {
    this.location.back();
  }

}





import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Segment } from '../segment';
import { SegmentService } from '../segment.service';

@Component({
  selector: 'app-segments',
  templateUrl: './segments.component.html',
  styleUrls: ['./segments.component.css']
})
export class SegmentsComponent implements OnInit {

  segments: Segment[];
  selectedSegment: Segment;

  constructor(private segmentService: SegmentService,
  private route: ActivatedRoute) { }

  getSegments() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.getSegments(id).then(segments => this.segments = segments);
  }

  ngOnInit(): void {
    this.getSegments();
  }
  onSelect(seg: Segment): void {
    this.selectedSegment = seg;
  }

}



  














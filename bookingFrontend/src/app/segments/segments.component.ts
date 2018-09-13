import { Component, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Segment } from '../segment';
import { SegmentService } from '../segment.service';
import { Hall } from '../hall';

@Component({
  selector: 'app-segments',
  templateUrl: './segments.component.html',
  styleUrls: ['./segments.component.css']
})
export class SegmentsComponent implements OnInit, OnChanges {
  @Input() hall: Hall;
  segments: Segment[];
  selectedSegment: Segment;

  constructor(private segmentService: SegmentService,
  private route: ActivatedRoute) { }

  getSegments() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.getSegments(id, this.hall.id).then(segments => this.segments = segments);
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.getSegments();
    this.selectedSegment = null;    
  }

  ngOnInit(): void {
    this.getSegments();
  }
  onSelect(seg: Segment): void {
    this.selectedSegment = seg;
  }

}


















import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FormControl} from '@angular/forms';
import { Happening } from '../happening';
import { Segment } from '../segment';
import { HappeningService } from '../happening.service';
import { SegmentService } from '../segment.service';

@Component({
  selector: 'app-happening-details',
  templateUrl: './happening-details.component.html',
  styleUrls: ['./happening-details.component.css']
})
export class HappeningDetailsComponent  {

  @Input() happening: Happening;
  segmentControl = new FormControl();
  segments: Segment[];
 
  constructor(private happeningService: HappeningService,
    private route: ActivatedRoute,
    private segmentService: SegmentService) {}

  delete(): void {
    this.happeningService.delete(this.happening.id).then(() => this.goBack());
  }

  save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentControl.value.forEach(element => {
      this.happeningService.addSegment(element,id,this.happening.id,element.id);
    });   
    this.happeningService.update(id,this.happening).then(() => this.goBack());
  }

  getSegments() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.getSegments(id).then(segments => this.segments = segments);
  }

  ngOnInit(): void {
    this.getSegments();
  }
 
  goBack(): void {
    window.location.replace('');
  }

}




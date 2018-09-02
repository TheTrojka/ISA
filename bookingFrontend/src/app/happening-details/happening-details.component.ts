import { Component, Input, OnInit , SimpleChanges, SimpleChange, OnChanges} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FormControl} from '@angular/forms';
import { Happening } from '../happening';
import { Segment } from '../segment';
import { HappeningService } from '../happening.service';
import { SegmentService } from '../segment.service';
import { DataService } from '../data.service';
import { Timing } from '../timing';
import { Seat } from '../seat';

@Component({
  selector: 'app-happening-details',
  templateUrl: './happening-details.component.html',
  styleUrls: ['./happening-details.component.css']
})
export class HappeningDetailsComponent implements OnInit , OnChanges {

  @Input() happening: Happening;
  timingSeatControl = new FormControl();
  segments: Segment[];

  timings: Timing[];
  timingSeats: Seat[];
  discountNumber: number;
  timingNum: number;
  admin = false;
  Eadmin = false;
  user = false;
  timingsSearched = false;
  timingSeatsSearched = false;

  constructor(private happeningService: HappeningService,
    private route: ActivatedRoute,
    private segmentService: SegmentService,
    private dataService: DataService) {}

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
    this.timingsSearched = true;
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getTimings(id, this.happening.id).then(timings => this.timings = timings)
    .then(() => {
      this.timings.forEach( e => { e.canBook = Date.parse(this.fixDate(e.time)) > Date.now();
      console.log(this.fixDate(e.time));
      console.log(Date.now());
      console.log(Date.parse(this.fixDate(e.time)) < Date.now());
    });
    });
  }

  getTimingSeats(timingId: number) {
    this.timingSeatsSearched = true;
    this.timingNum = timingId;
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getTimingSeats(id, this.happening.id, timingId)
    .then(timingSeats => this.timingSeats = timingSeats);
  }

  book(timingId: number) {
    this.timingSeatControl.value.forEach(element => {
      this.happeningService.book(JSON.parse(localStorage.getItem('user'))['id'], timingId, element)
      .then(() => this.goBack())
      .catch(() => alert('Already booked please refresh browser'));
    });
    
  }

  discount(timingId: number) {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    console.log(this.discountNumber);
    this.timingSeatControl.value.forEach(element => {
      this.happeningService.discount(id, this.happening.id , timingId, element, this.discountNumber)
      .then(() => this.goBack())
      .catch(() => alert('Already discounted please refresh browser'));
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    this.timings = null;
  }

  ngOnInit(): void {
    if (localStorage.getItem('userRole')) {
      this.user = true;
    } else if (localStorage.getItem('admin')) {
      this.admin = true;
    } else if (localStorage.getItem('Eadmin')) {
      this.checkAdmin();
    }
    this.getTimings();
    this.getSegments();   
  }

  checkAdmin() {
    this.dataService.checkIfAdmin(+this.route.snapshot.paramMap.get('establishmentId')
    , JSON.parse(localStorage.getItem('user'))['id']).then(discounts => this.Eadmin = true);
    localStorage.setItem('Eadmin', 'Eadmin');
  }

  fixDate(date): string {
    const separators = [' ', '/', ':'];
    const splitted = date.split(new RegExp(separators.join('|'), 'g'), 6);
    const returnString = splitted[2] + '-' + splitted[1] + '-' + splitted[0] + 'T' + splitted[3] + ':' + splitted[4];
    return returnString; 
  }

  goBack(): void {
    window.location.replace('');
  }

}




import { Component, Input, OnInit, SimpleChanges, OnChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';
import { Happening } from '../happening';
import { Seat } from '../seat';
import { Segment } from '../segment';
import { HappeningService } from '../happening.service';
import { SegmentService } from '../segment.service';
import { SearchEstablishmentsComponent } from '../search-establishments/search-establishments.component';
import { Hall } from '../hall';
import { Timing } from '../timing';

@Component({
  selector: 'app-segment-details',
  templateUrl: './segment-details.component.html',
  styleUrls: ['./segment-details.component.css']
})
export class SegmentDetailsComponent implements OnChanges {
  @Input() hall: Hall;
  @Input() segment: Segment;
  seats: Seat[];
  checkTimings: Timing[];
  seatingNum: number;
  seatCount: number;

  constructor(
    private route: ActivatedRoute,
    private segmentService: SegmentService) { }

  delete(): void {
    let taken = false;
    let oef = '';
    this.segmentService.checkForDeleteSeg( this.segment.id, this.hall.id)
      .then(timings =>
        this.checkTimings = timings)
      .then(() => this.checkTimings.forEach(e => {
        taken = Date.parse(this.fixDate(e.time)) > Date.now();
        if (taken) {
          oef += 'de';
        }
      }))
      .then(() => {
        console.log(oef);        
        if (oef === '') {
          this.segmentService.delete(this.segment.id, this.hall.id).then(() => this.goBack());
        } else {
          alert('Segment in use');
        }
      });
  }

  save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.update(id, this.hall.id, this.segment).then(() => this.goBack());
  }

  goBack(): void {
    window.location.replace('');
  }

  ngOnChanges(changes: SimpleChanges): void {
    // this.;    
  }

  getSeats() {
    this.seatCount = 0;
    this.seatingNum = this.segment.id;
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.getSeats(id, this.hall.id, this.segment.id)
    .then(seats => this.seats = seats)
    .then(() => {
      this.seats.forEach(e => {
        if (e.active) {
          this.seatCount++;
        }
      });
    });
    console.log(this.segment.id);
  }

  deleteSeat(seatId: number) {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    let taken = false;
    let oef = '';
    this.segmentService.checkForDelete(id, this.hall.id, this.segment.id, seatId)
      .then(timings =>
        this.checkTimings = timings)
      .then(() => this.checkTimings.forEach(e => {
        taken = Date.parse(this.fixDate(e.time)) > Date.now();
        if (taken) {
          oef += 'de';
        }
      }))
      .then(() => {
        console.log(oef);
        if (oef === '') {
          this.segmentService.deleteSeat(id, this.hall.id, this.segment.id, seatId).then(() => this.getSeats());
        } else {
          alert('Seat in use');
        }
      });
  }

  addSeat() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.addSeat(id, this.hall.id, this.segment.id)
    .then(() => this.getSeats());
  }

  fixDate(date): string {
    const separators = [' ', '/', ':'];
    const splitted = date.split(new RegExp(separators.join('|'), 'g'), 6);
    const returnString = splitted[2] + '-' + splitted[1] + '-' + splitted[0] + 'T' + splitted[3] + ':' + splitted[4];
    return returnString;
  }

}





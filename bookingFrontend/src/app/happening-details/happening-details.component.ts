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
  checkTimings: Timing[];
  seatSegments: String[];
  timingSeats: Seat[];
  discountNumber: number;
  timingNum: number;
  admin = false;
  Eadmin = false;
  user = false;
  timingsSearched = false;
  timingSeatsSearched = false;
  base64textString = [];
  expired = true;

  constructor(private happeningService: HappeningService,
    private route: ActivatedRoute,
    private segmentService: SegmentService,
    private dataService: DataService) {}

  delete(): void {   
    let taken = false;
    let oef = '';
    this.happeningService.checkForDelete(this.happening.id)
      .then(timings =>
        this.checkTimings = timings)
      .then(() => this.checkTimings.forEach(e => {
        taken = Date.parse(this.fixDate(e.time)) > Date.now();
        if (taken) {
          oef += 'de';
        }
      }))
      .then(() => {     
        if (oef === '') {
          this.happeningService.delete(this.happening.id).then(() => this.goBack());
        } else {
          alert('Happening has future dates');
        }
      });
  }

  save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    if (this.base64textString[0]) {
      this.happening.picture = this.base64textString[0];
    }
    this.happeningService.update(id, this.happening).then(() => this.goBack())
    .catch(() => alert('Happening already exists'));
  }

  getSegments() {
    const id = +this.route.snapshot.paramMap.get('establishmentId'); 
  }

  getTimings() {
    this.timingsSearched = true;
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getTimings(id, this.happening.id).then(timings => this.timings = timings)
    .then(() => {
      this.timings.forEach( e => { e.canBook = Date.parse(this.fixDate(e.time)) > Date.now();
        if (e.canBook) {
          this.expired = false;
        }
      });
    })
    .then(() => {
      this.timings.forEach( e =>
          this.happeningService.getTimingHall(id, this.happening.id, e.id)
          .then(hall => e.hall = hall.name));  
      });     
  }

  getTimingSeats(timingId: number) {
    this.timingSeatsSearched = true;
    this.timingNum = timingId;
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getTimingSeats(id, this.happening.id, timingId)
    .then(timingSeats => this.timingSeats = timingSeats)
    .then(() => {
      let counter = 0;
      this.timingSeats.forEach(e => this.happeningService.getTimingSeatSegment
      (id, this.happening.id, e.id)
      .then(segment => e.hall = segment.name)
      .then(() => {
        counter++;
        if (counter === this.timingSeats.length) {
        this.seatSegments = this.timingSeats.map(a => a.hall);       
        this.seatSegments = this.seatSegments.filter(function(elem, index, self) {
          return index === self.indexOf(elem);
        });
      }

      }));
    });    
  }

  book(timingId: number) {
    if (this.timingSeatControl.value && this.timingSeatControl.value.length > 0) {
    this.timingSeatControl.value.forEach(element => {
      this.happeningService.book(JSON.parse(localStorage.getItem('user'))['id'], timingId, element)
      .then(() => this.goBack())
      .catch(() => alert('Already booked please refresh browser'));
    });
  } else {
    alert('Pick seat/s');
  }
    
  }

  discount(timingId: number) {
    if (this.timingSeatControl.value && this.timingSeatControl.value.length > 0 && this.discountNumber) {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    console.log(this.discountNumber);
    this.timingSeatControl.value.forEach(element => {
      this.happeningService.discount(id, this.happening.id , timingId, element, this.discountNumber)
      .then(() => this.goBack())
      .catch(() => alert('Already discounted please refresh browser'));
    });
  } else {
    alert('Pick seat/s and discount percentage');
  } 
  }

  ngOnChanges(changes: SimpleChanges) {
    this.timings = null;
    this.timingsSearched = false;
  }

  ngOnInit(): void {   
    if (localStorage.getItem('userRole')) {
      this.user = true;
    } else if (localStorage.getItem('admin')) {
      this.admin = true;
    } else if (localStorage.getItem('Eadmin')) {
      this.checkAdmin();
    }
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
    console.log(returnString);
    return returnString; 
  }

  onUploadChange(evt: any) {
    const file = evt.target.files[0];
  
    if (file) {
      const reader = new FileReader();
  
      reader.onload = this.handleReaderLoaded.bind(this);
      reader.readAsBinaryString(file);
    }
  }
  
  handleReaderLoaded(e) {
    this.base64textString.push('data:image/png;base64,' + btoa(e.target.result));
  }

  goBack(): void {
    window.location.replace('');
  }

}




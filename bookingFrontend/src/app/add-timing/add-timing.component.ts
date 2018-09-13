import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Timing } from '../timing';
import { HappeningService } from '../happening.service';
import { SegmentService } from '../segment.service';
import { Location, DatePipe } from '@angular/common';
import { Segment } from '../segment';
import { FormControl } from '@angular/forms';
import { HallService } from '../hall.service';
import { Hall } from '../hall';
import { Happening } from '../happening';

@Component({
  selector: 'app-add-timing',
  templateUrl: './add-timing.component.html',
  styleUrls: ['./add-timing.component.css']
})
export class AddTimingComponent implements OnInit {

  timing = new Timing;
  checkTimings: Timing[];
  segmentControl = new FormControl();
  hallControl = new FormControl();
  submitted = false;
  segments: Segment[];
  allSegments: Segment[];
  halls: Hall[];
  allHalls: Hall[];
  date: Date;
  happening: Happening;

  constructor(private happeningService: HappeningService,
    private segmentService: SegmentService,
    private hallService: HallService,
    private route: ActivatedRoute,
    private location: Location,
    private datepipe: DatePipe) { }

  ngOnInit() {
    this.getHalls();
  }

  newTiming(): void {
    this.submitted = false;
    this.timing = new Timing();
  }

  private save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    const happId = +this.route.snapshot.paramMap.get('happeningId');
    const latest_date = this.datepipe.transform(this.timing.time, 'dd/MM/yyyy HH:mm:ss');
    this.date = this.timing.time;
    console.log(this.timing.time);
    console.log(latest_date);
    this.hallService.checkForDelete(id, this.hallControl.value)
      .then(timings =>
        this.checkTimings = timings)
      .then(() =>
        this.happeningService.getHappening(id, happId)
          .then(happening =>
            this.happening = happening)
          .then(() => {
            let taken = false;
            let oef = '';
            let counter = 0;
            this.checkTimings.forEach(e => {
              let withDuration = new Timing;
              this.hallService.checkIfBusy(e.id)
                .then(timing => withDuration = timing)
                .then(() => {
                  counter++;
                  taken = (((Date.parse(this.fixDate(e.time)) < (Date.parse(this.fixDate(latest_date))))
                    && ((Date.parse(this.fixDate(latest_date))) < Date.parse(this.fixDate(withDuration.time))))
                    || ((Date.parse(this.fixDate(e.time)) < (Date.parse(this.fixDate(latest_date)) + this.happening.duration * 60000))
            && ((Date.parse(this.fixDate(latest_date)) + this.happening.duration * 60000) < Date.parse(this.fixDate(withDuration.time)))));
                  if (taken) {
                    oef += 'de';
                  }
                  if (counter === this.checkTimings.length) {
                    if (oef === '') {
                      console.log(oef);
                      this.happeningService.addTiming(latest_date, id, happId)
                        .then(timing => this.segmentControl.value.forEach(element => {
                          this.happeningService.addTimingSeg(id, happId, timing.id, element);
                        }));
                    } else {
                      alert('Hall taken for that timing');
                    }
                  }
                });
            });
          }
          ));
  }

  getHalls() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.hallService.getHalls(id).then(halls => this.allHalls = halls)
      .then(() => {
        this.halls = new Array();
        this.allHalls.forEach(e => {
          if (e.active) {
            this.halls.push(e);
          }
        });
      });
  }

  getSegments() {
    this.segments = null;
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.segmentService.getSegments(id, this.hallControl.value).then(segments => this.allSegments = segments)
      .then(() => {
        this.segments = new Array();
        this.allSegments.forEach(e => {
          if (e.active) {
            this.segments.push(e);
          }
        });
      });
  }

  onSubmit() {
    this.save();
    this.goBack();
  }

  goBack(): void {
    this.location.back();
  }

  fixDate(date): string {
    const separators = [' ', '/', ':'];
    const splitted = date.split(new RegExp(separators.join('|'), 'g'), 6);
    const returnString = splitted[2] + '-' + splitted[1] + '-' + splitted[0] + 'T' + splitted[3] + ':' + splitted[4];
    return returnString;
  }

}









import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FormControl} from '@angular/forms';
import { Happening } from '../happening';
import { Seat } from '../seat';
import { Hall } from '../hall';
import { HappeningService } from '../happening.service';
import { HallService } from '../hall.service';
import { SearchEstablishmentsComponent } from '../search-establishments/search-establishments.component';
import { Timing } from '../timing';

@Component({
  selector: 'app-hall-details',
  templateUrl: './hall-details.component.html',
  styleUrls: ['./hall-details.component.css']
})
export class HallDetailsComponent {

  @Input() hall: Hall;
  checkTimings: Timing[];

  constructor(
    private route: ActivatedRoute,
    private hallService: HallService) {}

  delete(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    let taken = false;
    let oef = '';
    this.hallService.checkForDelete(id, this.hall.id)
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
          this.hallService.delete(id, this.hall.id).then(() => this.goBack());
        } else {
          alert('Segment in use');
        }
      });
  }

  save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.hallService.update(id, this.hall).then(() => this.goBack())
    .catch(() => alert('Name already exists'));
  }

  goBack(): void {
    window.location.replace('');
  }

  fixDate(date): string {
    const separators = [' ', '/', ':'];
    const splitted = date.split(new RegExp(separators.join('|'), 'g'), 6);
    const returnString = splitted[2] + '-' + splitted[1] + '-' + splitted[0] + 'T' + splitted[3] + ':' + splitted[4];
    return returnString;
  }



}






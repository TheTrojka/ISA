import { Component, OnInit } from '@angular/core';
import { Fanzone } from '../fanzone';
import { FanzoneService } from '../fanzone.service';

@Component({
  selector: 'app-fanzone',
  templateUrl: './fanzone.component.html',
  styleUrls: ['./fanzone.component.css']
})
export class FanzoneComponent implements OnInit {

 // fanzone: Fanzone[];
  // selectedFanzone: Fanzone;

  admin = false;
  user = false;

  constructor() { }
/*
  getFanzones() {
    this.fanzoneService.getFanZones().then(fanzone => this.fanzone = fanzone);
  }
*/
  ngOnInit(): void {
    if (localStorage.getItem('FZadmin')) {
      this.admin = true;     
    } else if (localStorage.getItem('userRole')) {
      this.user = true; 
    } 
   // this.getFanzones();
  }
  /*
  onSelect(est: Fanzone): void {
    this.selectedFanzone = est;
  }
  */

}

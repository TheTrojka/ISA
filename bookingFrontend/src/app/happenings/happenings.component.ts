import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Happening } from '../happening';
import { DataService } from '../data.service';
import { HappeningService } from '../happening.service';

@Component({
  selector: 'app-happenings',
  templateUrl: './happenings.component.html',
  styleUrls: ['./happenings.component.css']
})
export class HappeningsComponent implements OnInit {

  happenings: Happening[];
  selectedHappening: Happening;

  admin = false;
  Eadmin = false;
  user = false;

  constructor(private happeningService: HappeningService,
    private dataService: DataService,
    private route: ActivatedRoute) { }

  getHappenings() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getHappenings(id).then(happenings => this.happenings = happenings);
  }

  ngOnInit(): void {
    this.getHappenings();
    if (localStorage.getItem('userRole')) {
      this.user = true;
    } else if (localStorage.getItem('admin')) {
      this.admin = true;
    } else if (localStorage.getItem('Eadmin')) {
      this.checkAdmin();
    }
  }

  checkAdmin() {
    this.dataService.checkIfAdmin(+this.route.snapshot.paramMap.get('establishmentId')
    , JSON.parse(localStorage.getItem('user'))['id']).then(discounts => this.Eadmin = true);
    localStorage.setItem('Eadmin', 'Eadmin');
  }

  onSelect(happ: Happening): void {
    console.log('no');
    this.selectedHappening = happ;
  }

}
















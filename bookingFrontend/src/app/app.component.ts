import { Component, OnInit } from '@angular/core';
import { Fanzone } from './fanzone';
import { FanzoneService } from './fanzone.service';
import { DataService } from './data.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  loggedin = false;
  admin = false;
  Eadmin = false;
  FZadmin = false;
  user = false;
  eid: number;

  constructor(private dataService: DataService) {
  }

  ngOnInit() {
    if (localStorage.getItem('user') != null) {
      if (JSON.parse(localStorage.getItem('user')) != null) {
        this.loggedin = true;
      } if (JSON.parse(localStorage.getItem('user'))['role'] === 'admin') {
        this.admin = true;
        localStorage.setItem('admin', 'admin');
      } else if (JSON.parse(localStorage.getItem('user'))['role'] === 'user') {
        this.user = true; 
        localStorage.setItem('userRole', 'userRole');
      } else if (JSON.parse(localStorage.getItem('user'))['role'] === 'establishmentAdmin') {
        this.dataService.getAdminEstablishment(+JSON.parse(localStorage.getItem('user'))['id'])
        .then(est => this.eid = est.id);
        this.Eadmin = true;
        localStorage.setItem('Eadmin', 'Eadmin');
      } else if (JSON.parse(localStorage.getItem('user'))['role'] === 'fanZoneAdmin') {
        this.FZadmin = true;
        localStorage.setItem('FZadmin', 'FZadmin');
      }
    }
  }

  logout() {
    localStorage.clear();
    window.location.href = 'http://localhost:4200/';
  }

}

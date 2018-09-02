import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Location, DatePipe} from '@angular/common';
import { Report } from '../report';
import { DataService } from '../data.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  from = new Date;
  to = new Date;
  report = new Report;
  constructor(private route: ActivatedRoute,
    private location: Location,
    private datepipe: DatePipe,
    private dataService: DataService) {}

  ngOnInit() {
  }

  private generateReport(): void {
    const from = this.datepipe.transform(this.from, 'dd/MM/yyyy HH:mm:ss');
    const to = this.datepipe.transform(this.to, 'dd/MM/yyyy HH:mm:ss');
    const dates = new Array(from, to);
    this.dataService.attendanceReport(dates,
       JSON.parse(localStorage.getItem('user'))['id']).then(visits => this.report.attendance = visits);
    this.dataService.profitReport(dates,
       JSON.parse(localStorage.getItem('user'))['id']).then(profit => this.report.profit = profit);
  }

  onSubmit() {
    this.generateReport();
  }

  goBack(): void {
    this.location.back();
  }

}

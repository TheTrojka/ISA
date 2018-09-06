import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location, DatePipe } from '@angular/common';
import { Report } from '../report';
import { DataService } from '../data.service';
import { Establishment } from '../establishment';
import { Happening } from '../happening';
import { HappeningService } from '../happening.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  // lineChart
  public lineChartData = { data: [], label: 'Attendance' };
  public lineChartLabels: Array<any> = [];
  public lineChartDataWeekly = { data: [], label: 'Attendance' };
  public lineChartLabelsWeekly: Array<any> = [];
  public lineChartDataMonthly = { data: [], label: 'Attendance' };
  public lineChartLabelsMonthly: Array<any> = [];
  public lineChartOptions = {
    responsive: true
  };
  public lineChartColors = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend = false;
  public lineChartType = 'line';
  from = new Date;
  to = new Date;
  report = new Report;
  day = false;
  week = false;
  month = false;
  establishment: Establishment;
  happenings: Happening[];
  constructor(private route: ActivatedRoute,
    private location: Location,
    private datepipe: DatePipe,
    private dataService: DataService,
    private happeningService: HappeningService) { }

  ngOnInit() {
    this.visitsDaily();
    this.visitsWeekly();
    this.visitsMonthly();
    this.getEstablishment();
    this.getHappenings();
  }

  private generateReport(): void {
    console.log(this.from);
    console.log(this.to);
    if (this.from.valueOf() > this.to.valueOf()) {
      alert('From field has to be before To field');
    } else if (!this.from || !this.to) {
      alert('Fields cant be empty');
    } else {
      const from = this.datepipe.transform(this.from, 'dd/MM/yyyy HH:mm:ss');
      const to = this.datepipe.transform(this.to, 'dd/MM/yyyy HH:mm:ss');
      const dates = new Array(from, to);
      this.dataService.attendanceReport(dates,
        JSON.parse(localStorage.getItem('user'))['id']).then(visits => this.report.attendance = visits);
      this.dataService.profitReport(dates,
        JSON.parse(localStorage.getItem('user'))['id']).then(profit => this.report.profit = profit);
    }
  }

  private enableDaily(): void {
    this.day = true;
    this.week = false;
    this.month = false;
  }

  private enableWeekly(): void {
    this.day = false;
    this.week = true;
    this.month = false;
  }

  private enableMonthly(): void {
    this.day = false;
    this.week = false;
    this.month = true;
  }

  private visitsDaily(): void {
    const date = this.datepipe.transform(Date.now(), 'dd/MM/yyyy HH:mm:ss');
    this.dataService.visitDaily(date,
      JSON.parse(localStorage.getItem('user'))['id']).then(visitPoints => {
        console.log(visitPoints);
        this.lineChartData.data = visitPoints.map(obj => {
          return +obj.point;
        });
        this.lineChartLabels = visitPoints.map(obj => {
          return obj.date;
        });
      })
      .then(() => {
        this.lineChartData.data = this.lineChartData.data.reverse();
        this.lineChartLabels = this.lineChartLabels.reverse();
      });
  }

  private visitsWeekly(): void {
    const date = this.datepipe.transform(Date.now(), 'dd/MM/yyyy HH:mm:ss');
    this.dataService.visitWeekly(date,
      JSON.parse(localStorage.getItem('user'))['id']).then(visitPoints => {
        console.log(visitPoints);
        this.lineChartDataWeekly.data = visitPoints.map(obj => {
          return +obj.point;
        });
        this.lineChartLabelsWeekly = visitPoints.map(obj => {
          return obj.date;
        });
      })
      .then(() => {
        this.lineChartDataWeekly.data = this.lineChartDataWeekly.data.reverse();
        this.lineChartLabelsWeekly = this.lineChartLabelsWeekly.reverse();
      });
  }

  private visitsMonthly(): void {
    const date = this.datepipe.transform(Date.now(), 'dd/MM/yyyy HH:mm:ss');
    this.dataService.visitMonthly(date,
      JSON.parse(localStorage.getItem('user'))['id']).then(visitPoints => {
        console.log(visitPoints);
        this.lineChartDataMonthly.data = visitPoints.map(obj => {
          return +obj.point;
        });
        this.lineChartLabelsMonthly = visitPoints.map(obj => {
          return obj.date;
        });
      })
      .then(() => {
        this.lineChartDataMonthly.data = this.lineChartDataMonthly.data.reverse();
        this.lineChartLabelsMonthly = this.lineChartLabelsMonthly.reverse();
      });
  }

  getEstablishment(): void {
    this.dataService.getEstablishmentByUser(JSON.parse(localStorage.getItem('user'))['id'])
      .then(establishment => this.establishment = establishment);
  }

  getHappenings() {
    this.happeningService.getHappeningsByUser(JSON.parse(localStorage.getItem('user'))['id'])
      .then(happenings => this.happenings = happenings);
  }

  onSubmit() {
    this.generateReport();
  }

  goBack(): void {
    this.location.back();
  }

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

}

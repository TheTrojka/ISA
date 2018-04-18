import { Component, OnInit } from '@angular/core';
import { Establishment } from '../establishment';
import { DataService } from '../data.service';


@Component({
  selector: 'app-establishments',
  templateUrl: './establishments.component.html',
  styleUrls: ['./establishments.component.css']
})
export class EstablishmentsComponent implements OnInit {

  establishments: Establishment[];
  selectedEstablishment: Establishment;

  constructor(private dataService: DataService) { }

  getEstablishments() {
    this.dataService.getEstablishments().then(establishments => this.establishments = establishments);
  }

  ngOnInit(): void {
    this.getEstablishments();
  }

  onSelect(est: Establishment): void {
    this.selectedEstablishment = est;
  }

}










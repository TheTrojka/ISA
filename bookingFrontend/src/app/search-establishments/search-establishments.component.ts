import { Component, OnInit } from '@angular/core';
import {Establishment} from '../establishment';
import {DataService} from '../data.service';

@Component({
  selector: 'app-search-establishments',
  templateUrl: './search-establishments.component.html',
  styleUrls: ['./search-establishments.component.css']
})
export class SearchEstablishmentsComponent implements OnInit {

  address: string;
  establishments: Establishment[];

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.address = '';
  }

}


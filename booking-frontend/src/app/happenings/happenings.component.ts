import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Happening } from '../happening';
import { HappeningService } from '../happening.service';

@Component({
  selector: 'app-happenings',
  templateUrl: './happenings.component.html',
  styleUrls: ['./happenings.component.css']
})
export class HappeningsComponent implements OnInit {

  happenings: Happening[];
  selectedHappening: Happening;

  constructor(private happeningService: HappeningService,
  private route: ActivatedRoute) { }

  getHappenings() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.happeningService.getHappenings(id).then(happenings => this.happenings = happenings);
  }

  ngOnInit(): void {
    this.getHappenings();
  }
  onSelect(happ: Happening): void {
    this.selectedHappening = happ;
  }

}


  













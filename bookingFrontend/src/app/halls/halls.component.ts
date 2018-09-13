import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Hall } from '../hall';
import { HallService } from '../hall.service';

@Component({
  selector: 'app-halls',
  templateUrl: './halls.component.html',
  styleUrls: ['./halls.component.css']
})
export class HallsComponent implements OnInit {

  halls: Hall[];
  selectedHall: Hall;

  constructor(private hallService: HallService,
  private route: ActivatedRoute) { }

  getHalls() {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    this.hallService.getHalls(id).then(halls => this.halls = halls);
  }

  ngOnInit(): void {
    this.getHalls();
  }
  onSelect(seg: Hall): void {
    this.selectedHall = seg;
  }

}

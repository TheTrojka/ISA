import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Establishment } from '../establishment';
import { DataService } from '../data.service';

@Component({
  selector: 'app-establishment-details',
  templateUrl: './establishment-details.component.html',
  styleUrls: ['./establishment-details.component.css'],
  providers: [DataService]
})
export class EstablishmentDetailsComponent {

  @Input() establishment: Establishment;
 
  constructor(private route: ActivatedRoute,
    private dataService: DataService,
    private location: Location) {}

    ngOnInit(): void {
      this.getEstablishment();
    }

    getEstablishment(): void {
      const id = +this.route.snapshot.paramMap.get('establishmentId');
      this.dataService.getEstablishment(id)
        .then(establishment => this.establishment = establishment);
    }

  delete(): void {
    this.dataService.delete(this.establishment.id).then(() => this.goBack());
  }

  save(): void {
    this.dataService.update(this.establishment).then(() => this.goBack());
  }
 
  goBack(): void {
    window.location.replace('');
  }
}

 
  
 
  

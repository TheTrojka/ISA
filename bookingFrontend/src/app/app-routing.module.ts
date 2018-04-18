import {CreateEstablishmentComponent} from './create-establishment/create-establishment.component';
import {EstablishmentsComponent} from './establishments/establishments.component';
import {EstablishmentDetailsComponent} from './establishment-details/establishment-details.component';
import {SearchEstablishmentsComponent} from './search-establishments/search-establishments.component';
import {CreateHappeningComponent} from './create-happening/create-happening.component';
import {HappeningsComponent} from './happenings/happenings.component';
import {CreateSegmentComponent} from './create-segment/create-segment.component';
import { AddTimingComponent } from './add-timing/add-timing.component';

import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {path: '', redirectTo: 'establishment', pathMatch: 'full'},
  {path: 'establishment', component: EstablishmentsComponent},
  {path: 'establishment/:establishmentId', component: EstablishmentDetailsComponent},
  {path: 'add', component: CreateEstablishmentComponent},
  {path: 'findbyaddress', component: SearchEstablishmentsComponent},
  {path: 'establishment/:establishmentId/happeningAdd', component: CreateHappeningComponent},
  {path: 'establishment/:establishmentId/segmentAdd', component: CreateSegmentComponent},
  {path: 'establishment/:establishmentId/addTiming/:happeningId', component: AddTimingComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}

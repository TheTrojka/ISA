import {CreateEstablishmentComponent} from './create-establishment/create-establishment.component';
import {EstablishmentsComponent} from './establishments/establishments.component';
import {EstablishmentDetailsComponent} from './establishment-details/establishment-details.component';
import {SearchEstablishmentsComponent} from './search-establishments/search-establishments.component';
import {CreateHappeningComponent} from './create-happening/create-happening.component';
import {HappeningsComponent} from './happenings/happenings.component';
 
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: 'establishment', pathMatch: 'full'},
  {path: 'establishment', component: EstablishmentsComponent},
  {path: 'establishment/:establishmentId', component: EstablishmentDetailsComponent},
  {path: 'add', component: CreateEstablishmentComponent},
  {path: 'findbyaddress', component: SearchEstablishmentsComponent},
  {path: 'establishment/:establishmentId/happeningAdd', component: CreateHappeningComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
 
export class AppRoutingModule {}

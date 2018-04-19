import {CreateEstablishmentComponent} from './create-establishment/create-establishment.component';
import {EstablishmentsComponent} from './establishments/establishments.component';
import {EstablishmentDetailsComponent} from './establishment-details/establishment-details.component';
import {SearchEstablishmentsComponent} from './search-establishments/search-establishments.component';
import {CreateHappeningComponent} from './create-happening/create-happening.component';
import { Fanzone } from './fanzone';
import {HappeningsComponent} from './happenings/happenings.component';
import {PropsComponent} from './props/props.component';
import {PropsAdsComponent} from './props-ads/props-ads.component';
import {CreateFanzoneComponent} from './create-fanzone/create-fanzone.component';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FanzoneComponent} from './fanzone/fanzone.component';
const routes: Routes = [
  {path: '', redirectTo: 'establishment', pathMatch: 'full'},
  {path: 'establishment', component: EstablishmentsComponent},
  {path: 'establishment/:establishmentId', component: EstablishmentDetailsComponent},
  {path: 'add', component: CreateEstablishmentComponent},
  {path: 'findbyaddress', component: SearchEstablishmentsComponent},
  {path: 'establishment/:establishmentId/happeningAdd', component: CreateHappeningComponent},
  {path: 'fanzone/props', component: PropsComponent},
  {path: 'propsAd', component: PropsAdsComponent},
  {path: 'addFanzone', component: CreateFanzoneComponent},
  {path: 'fanzone', component: FanzoneComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
 
export class AppRoutingModule {}

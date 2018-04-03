import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {MatSelectModule} from '@angular/material/select';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { DataService } from './data.service';
import { EstablishmentsComponent } from './establishments/establishments.component';
import { EstablishmentDetailsComponent } from './establishment-details/establishment-details.component';
import { CreateEstablishmentComponent } from './create-establishment/create-establishment.component';
import { SearchEstablishmentsComponent } from './search-establishments/search-establishments.component';
import { AppRoutingModule } from './/app-routing.module';
import {enableProdMode} from '@angular/core';
import { HappeningService } from './happening.service';
import { HappeningsComponent } from './happenings/happenings.component';
import { HappeningDetailsComponent } from './happening-details/happening-details.component';
import { CreateHappeningComponent } from './create-happening/create-happening.component';
import { SegmentsComponent } from './segments/segments.component';
import { CreateSegmentComponent } from './create-segment/create-segment.component';
import { SegmentDetailsComponent } from './segment-details/segment-details.component';
import { SegmentService } from './segment.service';


@NgModule({
  declarations: [
    AppComponent,
    EstablishmentsComponent,
    EstablishmentDetailsComponent,
    CreateEstablishmentComponent,
    SearchEstablishmentsComponent,
    HappeningsComponent,
    HappeningDetailsComponent,
    CreateHappeningComponent,
    SegmentsComponent,
    CreateSegmentComponent,
    SegmentDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatSelectModule,
    BrowserAnimationsModule
  ],
  providers: [DataService, HappeningService, SegmentService],
  bootstrap: [AppComponent]
})
export class AppModule { }


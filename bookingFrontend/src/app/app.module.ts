import { CommonModule, DatePipe } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import { MatSelectModule, MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { AgmCoreModule } from '@agm/core';
import { ChartsModule } from 'ng2-charts';
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
import { CreatePropsComponent } from './create-props/create-props.component';
import { PropsDetailsComponent } from './props-details/props-details.component';
import { PropsComponent } from './props/props.component';
import { PropsService } from './props.service';
import { CreatePropsAdComponent } from './create-props-ad/create-props-ad.component';
import { PropsAdDetailsComponent } from './props-ad-details/props-ad-details.component';
import { PropsAdsComponent } from './props-ads/props-ads.component';
import { PropsAdService } from './props-ad.service';
import { CreateFanzoneComponent } from './create-fanzone/create-fanzone.component';

import { FanzoneService } from './fanzone.service';
import { FanzoneComponent } from './fanzone/fanzone.component';
import { AddTimingComponent } from './add-timing/add-timing.component';
import { RegisterComponent } from './register/register.component';
import { UserService } from './user.service';
import { ConfirmComponent } from './confirm/confirm.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { AddUserComponent } from './add-user/add-user.component';
import { AdminConfirmationComponent } from './admin-confirmation/admin-confirmation.component';
import { ReportsComponent } from './reports/reports.component';
import { UsersComponent } from './users/users.component';
import { PropsAdReviewComponent } from './props-ad-review/props-ad-review.component';
import { InvitationDecisionComponent } from './invitation-decision/invitation-decision.component';
import { UserRankingComponent } from './user-ranking/user-ranking.component';



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
    SegmentDetailsComponent,
    CreatePropsComponent,
    PropsDetailsComponent,
    PropsComponent,
    CreatePropsAdComponent,
    PropsAdDetailsComponent,
    PropsAdsComponent,
    CreateFanzoneComponent,
    FanzoneComponent,
    AddTimingComponent,
    RegisterComponent,
    ConfirmComponent,
    LoginComponent,
    ProfileComponent,
    AddUserComponent,
    AdminConfirmationComponent,
    ReportsComponent,
    UsersComponent,
    PropsAdReviewComponent,
    InvitationDecisionComponent,
    UserRankingComponent

  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatSelectModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDQzCvobOovm8PchTEARrZnr_DNZzQ40s8'
    }),
    ChartsModule
  ],

  providers: [DataService, HappeningService, FanzoneService, SegmentService, DatePipe,
     PropsService, PropsAdService, UserService],

  bootstrap: [AppComponent]
})
export class AppModule { }


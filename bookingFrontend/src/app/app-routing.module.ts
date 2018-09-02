import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
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
import {CreateSegmentComponent} from './create-segment/create-segment.component';
import { AddTimingComponent } from './add-timing/add-timing.component';
import {FanzoneComponent} from './fanzone/fanzone.component';
import {CreatePropsComponent} from './create-props/create-props.component';
import {PropsDetailsComponent} from './props-details/props-details.component';
import {CreatePropsAdComponent} from './create-props-ad/create-props-ad.component';
import {PropsAdDetailsComponent} from './props-ad-details/props-ad-details.component';
import {PropsAdReviewComponent} from './props-ad-review/props-ad-review.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ConfirmComponent } from './confirm/confirm.component';
import { ProfileComponent } from './profile/profile.component';
import { AddUserComponent } from './add-user/add-user.component';
import { AdminConfirmationComponent } from './admin-confirmation/admin-confirmation.component';
import { ReportsComponent } from './reports/reports.component';
import { UsersComponent } from './users/users.component';
import { InvitationDecisionComponent } from './invitation-decision/invitation-decision.component';
import { UserRankingComponent } from './user-ranking/user-ranking.component';

const routes: Routes = [
  {path: '', redirectTo: 'establishment', pathMatch: 'full'},
  {path: 'establishment/list/:listType', component: EstablishmentsComponent, data: {requiresLogin: true}},
  {path: 'establishment/:establishmentId', component: EstablishmentDetailsComponent},
  {path: 'add', component: CreateEstablishmentComponent},
  {path: 'findbyaddress', component: SearchEstablishmentsComponent},
  {path: 'establishment/:establishmentId/happeningAdd', component: CreateHappeningComponent},
  {path: 'fanzone/props', component: PropsComponent},
  {path: 'fanzone/propsAd', component: PropsAdsComponent},
  {path: 'fanzone', component: FanzoneComponent},
  {path: 'establishment/:establishmentId/segmentAdd', component: CreateSegmentComponent},
  {path: 'establishment/:establishmentId/addTiming/:happeningId', component: AddTimingComponent},
  {path: 'fanzone/props/propsAdd', component: CreatePropsComponent},
  {path: 'fanzone/props/:propsId', component: PropsDetailsComponent},
  {path: 'fanzone/propsAd/AddpropsAd', component: CreatePropsAdComponent},
  {path: 'fanzone/propsAd/:propsAdId', component: PropsAdDetailsComponent},
  {path: 'fanzone/propsAdreview', component: PropsAdReviewComponent}, 
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'confirm/:token', component: ConfirmComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'addUser', component: AddUserComponent},
  {path: 'adminConfirmation', component: AdminConfirmationComponent},
  {path: 'reports', component: ReportsComponent},
  {path: 'users', component: UsersComponent},
  {path: 'visits/:resId/invite/:guestId/decide', component: InvitationDecisionComponent},
  {path: 'status', component: UserRankingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]  
})

export class AppRoutingModule {}

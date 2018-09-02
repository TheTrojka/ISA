import { Component, OnInit } from '@angular/core';
import { Establishment } from '../establishment';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';
import { User } from '../user';


@Component({
  selector: 'app-invitation-decision',
  templateUrl: './invitation-decision.component.html',
  styleUrls: ['./invitation-decision.component.css']
})
export class InvitationDecisionComponent implements OnInit {

  message = '';

  constructor(private userService: UserService,
    private route: ActivatedRoute) {
  }

  getInvitation() {
    const resId = +this.route.snapshot.paramMap.get('resId');
    const guestId = +this.route.snapshot.paramMap.get('guestId');
    this.userService.getInvitation(resId, guestId)
    .then(message => this.message = message);
  }

  ngOnInit(): void {
    this.getInvitation();
  }

  accept(): void {
    const resId = +this.route.snapshot.paramMap.get('resId');
    const guestId = +this.route.snapshot.paramMap.get('guestId');
    this.userService.acceptInvitation(resId, guestId)
    .then(() => this.goBack());
  }

  decline(): void {
    const resId = +this.route.snapshot.paramMap.get('resId');
    const guestId = +this.route.snapshot.paramMap.get('guestId');
    this.userService.declineInvitation(resId, guestId)
    .then(() => this.goBack());
  }

  goBack(): void {
    window.location.replace('');
  }


}











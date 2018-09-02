import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  constructor(private userService: UserService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    console.log('here');
    this.confirm();
  }

  private confirm(): void {
    const token = this.route.snapshot.paramMap.get('token'); 
    this.userService.confirm(token);     
  }

}

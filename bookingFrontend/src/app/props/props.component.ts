import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Props } from '../props';
import { PropsService } from '../props.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-props',
  templateUrl: './props.component.html',
  styleUrls: ['./props.component.css']
})
export class PropsComponent implements OnInit {

  props: Props[];
  selectedProps: Props;
  admin = false;
  user = false;

  constructor(private propsService: PropsService, private route: ActivatedRoute,
    private location: Location) { }

  getProps() {
   // const id = +this.route.snapshot.paramMap.get('fanzoneId');
    const id = 1;
    this.propsService.getProps().then(props => this.props = props);
  }

  ngOnInit(): void {
    this.getProps();
    if (localStorage.getItem('FZadmin')) {
      this.admin = true;     
    } else if (localStorage.getItem('user')) {
      this.user = true; 
    } 
  }

  buyProp(id): void {
    this.propsService.buy(id, JSON.parse(localStorage.getItem('user'))['id']).then(() => this.goBack());
  }

  onSelect(est: Props): void {
    this.selectedProps = est;
  }

  goBack(): void {
    this.location.back();
  }

}

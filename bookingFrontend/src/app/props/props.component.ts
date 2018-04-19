import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Props } from '../props';
import { PropsService } from '../props.service';

@Component({
  selector: 'app-props',
  templateUrl: './props.component.html',
  styleUrls: ['./props.component.css']
})
export class PropsComponent implements OnInit {

  props: Props[];
  selectedProps: Props;

  constructor(private propsService: PropsService, private route: ActivatedRoute) { }

  getProps() {
    const id = +this.route.snapshot.paramMap.get('fanzoneId');
    this.propsService.getProps(id).then(props => this.props = props);
  }

  ngOnInit(): void {
    this.getProps();
  }
  onSelect(est: Props): void {
    this.selectedProps = est;
  }

}

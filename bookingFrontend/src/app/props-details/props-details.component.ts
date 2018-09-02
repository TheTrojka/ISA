import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Props } from '../props';
import { PropsService } from '../props.service';

@Component({
  selector: 'app-props-details',
  templateUrl: './props-details.component.html',
  styleUrls: ['./props-details.component.css'],
  providers: [PropsService]
})
export class PropsDetailsComponent  implements OnInit {

   @Input() props: Props;
 
  constructor(private route: ActivatedRoute,
    private propsService: PropsService,
    private location: Location) {}

    ngOnInit(): void {
      this.getProps();
    }

    getProps(): void {
      const id = +this.route.snapshot.paramMap.get('propsId');
      this.propsService.getProp(id)
        .then(props => this.props = props);
    }

  delete(): void {
    this.propsService.delete(this.props.id).then(() => this.goBack());
  }

  save(): void {
    this.props.active = true;
    this.propsService.update(this.props).then(() => this.goBack())
    .catch(() => alert('Prop with the same name already exists'));
  }
 
  goBack(): void {
    window.location.replace('');
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Happening} from '../happening';
import {HappeningService} from '../happening.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-create-happening',
  templateUrl: './create-happening.component.html',
  styleUrls: ['./create-happening.component.css']
})
export class CreateHappeningComponent implements OnInit {

  happening = new Happening;
  submitted = false;
  base64textString = [];
  fileAdded = false;
  constructor(private happeningService: HappeningService,
    private route: ActivatedRoute,
    private location: Location) {}
 
  ngOnInit() {
    console.log('heh');
  }
 
  private save(): void {
    const id = +this.route.snapshot.paramMap.get('establishmentId');
    if (this.base64textString[0]) {
      this.happening.picture = this.base64textString[0];
    }
    this.happeningService.create(id, this.happening)
    .then(() => this.goBack())
    .catch(() => alert('Happening already exists'));
  }
 
  onSubmit() {
    this.save();
  }

  onUploadChange(evt: any) {
    const file = evt.target.files[0];
    this.fileAdded = false;
    if (file) {
      const reader = new FileReader();
      this.fileAdded = true;
      reader.onload = this.handleReaderLoaded.bind(this);
      reader.readAsBinaryString(file);
    }
  }
  
  handleReaderLoaded(e) {
    this.base64textString.push('data:image/png;base64,' + btoa(e.target.result));
  }
 
  goBack(): void {
    this.location.back();
  }

}


 
  
 
  


import { Component } from '@angular/core';
import { Fanzone } from './fanzone';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  title = 'app';
  
  fanzone: Fanzone[];
 
  
}

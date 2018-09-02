import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Fanzone } from './fanzone';

@Injectable()
export class FanzoneService {
  
  constructor(private http: Http) {}
/*
  private fanzoneUrl = 'http://localhost:8080/fanzone';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});
 
  constructor(private http: Http) {}
 
  // Get all customers
  getFanZones(): Promise<Fanzone[]> {
    return this.http.get(this.fanzoneUrl)
      .toPromise()
      .then(response => response.json() as Fanzone[])
      .catch(this.handleError);
  }
 
  getFanZone(id: number): Promise<Fanzone> {
    const url = `${this.fanzoneUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Fanzone)
      .catch(this.handleError);
  }
 
  create(fanzone: Fanzone): Promise<Fanzone> {
    
     return this.http
      .post(this.fanzoneUrl, JSON.stringify(fanzone), {headers : this.headers})
      .toPromise()
      .then(res => res.json() as Fanzone)
      .catch(this.handleError);
    
  }
 
  delete(id: number): Promise<void> {
    const url = `${this.fanzoneUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(fanzone: Fanzone): Promise<Fanzone> {
    
    return this.http
      .put(this.fanzoneUrl, JSON.stringify(fanzone), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Fanzone)
      .catch(this.handleError);
  }
 
  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
  */
}

import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Segment } from './segment';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class SegmentService {

  private segmentsUrl = 'establishment/:establishmentId/segment';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});
 
  constructor(private http: Http) {}
 
  // Get all customers
  getSegments(id: number): Promise<Segment[]> {
    return this.http.get(`establishment/${id}/segment`)
      .toPromise()
      .then(response => response.json() as Segment[])
      .catch(this.handleError);
  }
 
  create(id: number, segment: Segment): Promise<Segment> {
    return this.http
      .post(`establishment/${id}/segment`, JSON.stringify(segment), {headers : this.headers})
      .toPromise()
      .then(response => response.json() as Segment)
      .catch(this.handleError);
      
  }
 
  delete(id: number): Promise<void> {
    const url = `${this.segmentsUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(id: number, segment: Segment): Promise<Segment> {
    return this.http
      .put(`establishment/${id}/segment`, JSON.stringify(segment), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Segment)
      .catch(this.handleError);
  }
 
  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}



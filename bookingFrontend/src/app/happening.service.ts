import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Happening } from './happening';
import { Segment } from './segment';
import { Timing } from './timing';

@Injectable()
export class HappeningService {

  private happeningsUrl = 'establishment/:establishmentId/happening';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {}

  // Get all customers
  getHappenings(id: number): Promise<Happening[]> {
    return this.http.get(`establishment/${id}/happening`)
      .toPromise()
      .then(response => response.json() as Happening[])
      .catch(this.handleError);
  }

  create(id: number, happening: Happening): Promise<Happening> {
    return this.http
      .post(`establishment/${id}/happening`, JSON.stringify(happening), {headers : this.headers})
      .toPromise()
      .then(response => response.json() as Happening)
      .catch(this.handleError);

  }

  delete(id: number): Promise<void> {
    const url = `${this.happeningsUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(id: number, happening: Happening): Promise<Happening> {
    return this.http
      .put(`establishment/${id}/happening`, JSON.stringify(happening), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Happening)
      .catch(this.handleError);
  }

  addSegment(segment: Segment, establishmentId: number, happeningId: number, segmentId: number): Promise<Segment> {
    return this.http
      .post(`establishment/${establishmentId}/happening/${happeningId}/segment/${segmentId}`,
       JSON.stringify(segment), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Segment)
      .catch(this.handleError);
  }

  addTiming(timing: string, establishmentId: number, happeningId: number, segmentId: number): Promise<string> {
    return this.http
      .post(`establishment/${establishmentId}/happening/${happeningId}/segment/${segmentId}/timing`,
       JSON.stringify(timing), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as string)
      .catch(this.handleError);
  }

  getTimings(id: number, happeningId: number): Promise<Timing[]> {
    return this.http.get(`establishment/${id}/happening/${happeningId}/timing`)
      .toPromise()
      .then(response => response.json() as Timing[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

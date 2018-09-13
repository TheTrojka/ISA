import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Segment } from './segment';
import { Seat } from './seat';

import 'rxjs/add/operator/toPromise';
import { Timing } from './timing';

@Injectable()
export class SegmentService {

  private segmentsUrl = 'http://localhost:8080/establishment/:establishmentId/segment';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {}

  // Get all customers
  getSegments(id: number, hallId: number): Promise<Segment[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment`)
      .toPromise()
      .then(response => response.json() as Segment[])
      .catch(this.handleError);
  }


  getSeats(id: number, hallId: number, segmentId: number): Promise<Seat[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment/${segmentId}/seat`)
      .toPromise()
      .then(response => response.json() as Seat[])
      .catch(this.handleError);
  }

  deleteSeat(id: number, hallId: number, segmentId: number, seatId: number): Promise<void> {
    return this.http.delete(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment/${segmentId}/seat/${seatId}`)
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  addSeat(id: number, hallId: number, segmentId: Number): Promise<Seat> {
    return this.http.post(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment/${segmentId}/seat`, {id: '0'},
     {headers : this.headers})
      .toPromise()
      .then(response => response.json() as Seat)
      .catch(this.handleError);
  }

  checkForDelete(id: number, hallId: number, segmentId: number, seatId: number): Promise<Timing[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment/${segmentId}/seat/${seatId}/bookings`)
      .toPromise()
      .then(response => response.json() as Timing[])
      .catch(this.handleError);
  }


  create(id: number, hallId: number, segment: Segment): Promise<Segment> {
    return this.http
      .post(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment`,
       JSON.stringify(segment), {headers : this.headers})
      .toPromise()
      .then(response => response.json() as Segment)
      .catch(this.handleError);

  }

  delete(id: number, hallId: number): Promise<void> {
    return this.http.delete(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment/${id}`,
     {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  checkForDeleteSeg(id: number, hallId: number): Promise<Timing[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment/${id}/bookings`)
      .toPromise()
      .then(response => response.json() as Timing[])
      .catch(this.handleError);
  }

  update(id: number, hallId: number, segment: Segment): Promise<Segment> {
    return this.http
      .put(`http://localhost:8080/establishment/${id}/hall/${hallId}/segment`, JSON.stringify(segment), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Segment)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}



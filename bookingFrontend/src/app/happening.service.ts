import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Happening } from './happening';
import { Segment } from './segment';
import { Timing } from './timing';
import { Seat } from './seat';
import { Visit } from './visit';
import { log } from 'util';
import { Hall } from './hall';

@Injectable()
export class HappeningService {

  private happeningsUrl = 'http://localhost:8080/establishment/:establishmentId/happening';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {}

  getHappening(est: number, id: number): Promise<Happening> {
    return this.http.get(`http://localhost:8080/establishment/${est}/happening/${id}`)
      .toPromise()
      .then(response => response.json() as Happening)
      .catch(this.handleError);
  }

  // Get all customers
  getHappenings(id: number): Promise<Happening[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/happening`)
      .toPromise()
      .then(response => response.json() as Happening[])
      .catch(this.handleError);
  }

  getHappeningsByUser(id: number): Promise<Happening[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/happening/byUser`)
      .toPromise()
      .then(response => response.json() as Happening[])
      .catch(this.handleError);
  }

  create(id: number, happening: Happening): Promise<Happening> {
    return this.http
      .post(`http://localhost:8080/establishment/${id}/happening`, JSON.stringify(happening), {headers : this.headers})
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

  checkForDelete(id: number): Promise<Timing[]> {
    const url = `${this.happeningsUrl}/${id}/bookings`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Timing[])
      .catch(this.handleError);
  }

  update(id: number, happening: Happening): Promise<Happening> {
    return this.http
      .put(`http://localhost:8080/establishment/${id}/happening`, JSON.stringify(happening), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Happening)
      .catch(this.handleError);
  }

  addSegment(segment: Segment, establishmentId: number, happeningId: number, segmentId: number): Promise<Segment> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/happening/${happeningId}/segment/${segmentId}`,
       JSON.stringify(segment), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Segment)
      .catch(this.handleError);
  }

  addTiming(timing: string, establishmentId: number, happeningId: number): Promise<Timing> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/happening/${happeningId}/timing`,
       JSON.stringify(timing), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Timing)
      .catch(this.handleError);
  }

  addTimingSeg(establishmentId: number, happeningId: number, tid: number, id: number): Promise<string> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/happening/${happeningId}/timing/${tid}/segment/${id}`,
       'J', {headers: this.headers})
      .toPromise()
      .then(res => res.json() as string)
      .catch(this.handleError);
  }

  book(guestId: number, timingId: number, seatId: number): Promise<any> {
    return this.http
      .post(`http://localhost:8080/guests/${guestId}/reservations/timing/${timingId}/seat/${seatId}`,
      JSON.stringify('timing'), {headers: this.headers})
      .toPromise()
      .then(res => res.text())
      .catch(this.handleError);
  }

  discount(establishmentId: number, happeningId: number, timingId: number,
     seatId: number, discountNumber: number): Promise<string> {
    return this.http
// tslint:disable-next-line:max-line-length
.post(`http://localhost:8080/establishment/${establishmentId}/happening/${happeningId}/timing/${timingId}/discount/${seatId}/${discountNumber}`,
JSON.stringify('timing'), {headers: this.headers})     
      .toPromise()
      .then(res => res.json() as string)
      .catch(this.handleError);
  }

  getUserReservations(guestId: number): Promise<Visit[]> {
    return this.http
      .get(`http://localhost:8080/guests/${guestId}/reservations/visits`)
      .toPromise()
      .then(res => res.json() as Visit[])
      .catch(this.handleError);
  }

  getReservationHappening(guestId: number, reservationId: number): Promise<Happening> {
    return this.http
      .get(`http://localhost:8080/guests/${guestId}/reservations/${reservationId}`)
      .toPromise()
      .then(res => res.json() as Happening)
      .catch(this.handleError);
  }

  getTimings(id: number, happeningId: number): Promise<Timing[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/happening/${happeningId}/timing`)
      .toPromise()
      .then(response => response.json() as Timing[])
      .catch(this.handleError);
  }

  getTimingSeats(id: number, happeningId: number, timingId: number): Promise<Seat[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/happening/${happeningId}/timing/${timingId}/free`)
      .toPromise()
      .then(response => response.json() as Seat[])
      .catch(this.handleError);
  }

  getTimingHall(id: number, happeningId: number, timingId: number): Promise<Hall> {
    return this.http.get(`http://localhost:8080/establishment/${id}/happening/${happeningId}/timing/${timingId}/hall`)
      .toPromise()
      .then(response => response.json() as Hall)
      .catch(this.handleError);
  }

  getTimingSeatSegment(id: number, happeningId: number, timingId: number): Promise<Segment> {
    return this.http.get(`http://localhost:8080/establishment/${id}/happening/${happeningId}/timing/${timingId}/segment`)
      .toPromise()
      .then(response => response.json() as Segment)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}


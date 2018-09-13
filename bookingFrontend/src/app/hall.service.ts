import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Hall } from './hall';
import { Seat } from './seat';

import 'rxjs/add/operator/toPromise';
import { Timing } from './timing';

@Injectable()
export class HallService {

  private hallsUrl = 'http://localhost:8080/establishment/:establishmentId/hall';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {}

  // Get all customers
  getHalls(id: number): Promise<Hall[]> {
    return this.http.get(`http://localhost:8080/establishment/${id}/hall`)
      .toPromise()
      .then(response => response.json() as Hall[])
      .catch(this.handleError);
  }

  create(id: number, hall: Hall): Promise<Hall> {
    return this.http
      .post(`http://localhost:8080/establishment/${id}/hall`, JSON.stringify(hall), {headers : this.headers})
      .toPromise()
      .then(response => response.json() as Hall)
      .catch(this.handleError);

  }

  delete(establishmentId: number, id: number): Promise<void> {
    const url = `http://localhost:8080/establishment/${establishmentId}/hall/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  checkForDelete(establishmentId: number, id: number): Promise<Timing[]> {
    const url = `http://localhost:8080/establishment/${establishmentId}/hall/${id}/bookings`;
    return this.http.get(url)
      .toPromise()
      .then(res => res.json() as Timing[])
      .catch(this.handleError);
  }

  checkIfBusy(id: number): Promise<Timing> {
    const url = `http://localhost:8080/establishment/${id}/hall/${id}/busy`;
    return this.http.get(url)
      .toPromise()
      .then(res => res.json() as Timing)
      .catch(this.handleError);
  }

  update(id: number, hall: Hall): Promise<Hall> {
    return this.http
      .put(`http://localhost:8080/establishment/${id}/hall`, JSON.stringify(hall), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Hall)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}




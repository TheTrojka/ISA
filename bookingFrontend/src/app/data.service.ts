import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
 
import 'rxjs/add/operator/toPromise';
 
import { Establishment } from './establishment';
 
@Injectable()
export class DataService {

  private establishmentsUrl = 'establishment';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {}

  // Get all customers
  getEstablishments(): Promise<Establishment[]> {
    return this.http.get(this.establishmentsUrl)
      .toPromise()
      .then(response => response.json() as Establishment[])
      .catch(this.handleError);
  }

  getEstablishment(id: number): Promise<Establishment> {
    const url = `${this.establishmentsUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Establishment)
      .catch(this.handleError);
  }

  create(establishment: Establishment): Promise<Establishment> {
    return this.http
      .post(this.establishmentsUrl, JSON.stringify(establishment), {headers : this.headers})
      .toPromise()
      .then(res => res.json() as Establishment)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.establishmentsUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(establishment: Establishment): Promise<Establishment> {
    return this.http
      .put(this.establishmentsUrl, JSON.stringify(establishment), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Establishment)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

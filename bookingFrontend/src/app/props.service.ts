import { Injectable } from '@angular/core';

import { Headers, Http } from '@angular/http';
 
import 'rxjs/add/operator/toPromise';
 
import { Props } from './props';

@Injectable()
export class PropsService {

  private propsUrl = 'http://localhost:8080/fanzone/props';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});
 
  constructor(private http: Http) {}
 
  // Get all customers
  getProps(): Promise<Props[]> {
    return this.http.get('http://localhost:8080/fanzone/props')
      .toPromise()
      .then(response => response.json() as Props[])
      .catch(this.handleError);
  }
 
  getProp(id: number): Promise<Props> {
    const url = `${this.propsUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Props)
      .catch(this.handleError);
  }
 
  create(prop: Props): Promise<Props> {
    
    return this.http
      .post('http://localhost:8080/fanzone/props', JSON.stringify(prop), {headers : this.headers})
      .toPromise()
      .then(res => res.json() as Props)
      .catch(this.handleError);
  }
 
  delete(id: number): Promise<void> {
    const url = `${this.propsUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(prop: Props): Promise<Props> {
    
    return this.http
      .put('http://localhost:8080/fanzone/props', JSON.stringify(prop), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Props)
      .catch(this.handleError);
  }

  buy(id: number, user: number): Promise<Props> {
    const url = `${this.propsUrl}/${id}/buy/${user}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Props)
      .catch(this.handleError);
  }
 
  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}

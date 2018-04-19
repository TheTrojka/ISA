import { Injectable } from '@angular/core';

import { Headers, Http } from '@angular/http';
 
import 'rxjs/add/operator/toPromise';
 
import { Props } from './props';

@Injectable()
export class PropsService {

  private propsUrl = 'fanzone/:fanzoneId/props';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});
 
  constructor(private http: Http) {}
 
  // Get all customers
  getProps(id: number): Promise<Props[]> {
    return this.http.get('fanzone/${id}/props')
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
 
  create(id: number, prop: Props): Promise<Props> {
    
    return this.http
      .post('fanzone/${id}/props', JSON.stringify(prop), {headers : this.headers})
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

  update(id: number, prop: Props): Promise<Props> {
    
    return this.http
      .put('fanzone/${id}/props', JSON.stringify(prop), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Props)
      .catch(this.handleError);
  }
 
  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}

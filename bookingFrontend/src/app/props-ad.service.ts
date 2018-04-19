import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { PropsAd } from './props-ad';

@Injectable()
export class PropsAdService {

  private propsUrl = 'fanzone/:fanzoneId/propsAd';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});
 
  constructor(private http: Http) {}
 
  // Get all customers
  getPropsAd(id: number): Promise<PropsAd[]> {
    return this.http.get('fanzone/${id}/props')
      .toPromise()
      .then(response => response.json() as PropsAd[])
      .catch(this.handleError);
  }
 
  getPropAd(id: number): Promise<PropsAd> {
    const url = `${this.propsUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as PropsAd)
      .catch(this.handleError);
  }
 
  create(id: number, propAd: PropsAd): Promise<PropsAd> {
    
    return this.http
      .post('fanzone/${id}/props', JSON.stringify(propAd), {headers : this.headers})
      .toPromise()
      .then(res => res.json() as PropsAd)
      .catch(this.handleError);
  }
 
  delete(id: number): Promise<void> {
    const url = `${this.propsUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(id: number, propAd: PropsAd): Promise<PropsAd> {
    
    return this.http
      .put('fanzone/${id}/props', JSON.stringify(propAd), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as PropsAd)
      .catch(this.handleError);
  }
 
  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }


}

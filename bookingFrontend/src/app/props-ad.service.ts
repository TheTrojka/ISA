import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { PropsAd } from './props-ad';

@Injectable()
export class PropsAdService {

  private propsUrl = 'fanzone/propsAd';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});
 
  constructor(private http: Http) {}
 
  // Get all customers
  getPropsAd(): Promise<PropsAd[]> {
    return this.http.get('fanzone/propsAd')
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
 
  create(propAd: PropsAd): Promise<PropsAd> {
    
    return this.http
      .post('fanzone/propsAd', JSON.stringify(propAd), {headers : this.headers})
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

  update(propAd: PropsAd): Promise<PropsAd> {
    
    return this.http
      .put('fanzone/propsAd', JSON.stringify(propAd), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as PropsAd)
      .catch(this.handleError);
  }
 
  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }


}

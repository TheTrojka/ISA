import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { PropsAd } from './props-ad';
import { Offer } from './offer';

@Injectable()
export class PropsAdService {

  private propsUrl = 'http://localhost:8080/fanzone/propsAd';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});
 
  constructor(private http: Http) {}
 
  // Get all customers
  getPropsAd(): Promise<PropsAd[]> {
    return this.http.get('http://localhost:8080/fanzone/propsAd')
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
 
  create(propAd: PropsAd, seller: number): Promise<PropsAd> {
    const url = `http://localhost:8080/fanzone/propsAd/${seller}`;
    return this.http
      .post(url, JSON.stringify(propAd), {headers : this.headers})
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
      .put('http://localhost:8080/fanzone/propsAd', JSON.stringify(propAd), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as PropsAd)
      .catch(this.handleError);
  }

  getOffers(id: number): Promise<Offer[]> {
    const url = `${this.propsUrl}/${id}/offer`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Offer[])
      .catch(this.handleError);
  }

  getOwnOffer(id: number, user: number): Promise<Offer> {
    const url = `${this.propsUrl}/${id}/offer/${user}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Offer)
      .catch(this.handleError);
  }

  makeOffer(id: number, user: number, price: String): Promise<Offer> { 
    const url = `${this.propsUrl}/${id}/offer/${user}`;  
    return this.http
      .post(url, JSON.stringify(price), {headers : this.headers})
      .toPromise()
      .then(res => res.json() as Offer)
      .catch(this.handleError);
  }

  changeOffer(propId: number, id: number, offer: string): Promise<Offer> {
    const url = `${this.propsUrl}/${propId}/offer/${id}`;   
    return this.http
      .put(url, JSON.stringify(offer), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Offer)
      .catch(this.handleError);
  }

  acceptOffer(propId: number, id: number): Promise<PropsAd> {
    const url = `${this.propsUrl}/${propId}/offer/accept/${id}`;   
    return this.http
      .post(url, JSON.stringify('offer'), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as PropsAd)
      .catch(this.handleError);
  }

  accept(propId: number): Promise<PropsAd> {
    const url = `${this.propsUrl}/${propId}/reviewed`;   
    return this.http
      .post(url, JSON.stringify('offer'), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as PropsAd)
      .catch(this.handleError);
  }

  removeOffer(propsId: number, id: number): Promise<void> {
    const url = `${this.propsUrl}/${propsId}/offer/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  getUser(propsId: number, id: number): Promise<any> {
    const url = `${this.propsUrl}/${propsId}/offer/${id}/user`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.text());
  }

  getSeller(id: number, user: number): Promise<any> {
    const url = `${this.propsUrl}/${id}/seller/${user}`;
    return this.http.get(url, {headers : this.headers})
      .toPromise()
      .then(response => response.text());
  }
  
 
  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }


}

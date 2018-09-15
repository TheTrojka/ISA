import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Establishment } from './establishment';
import { Discount } from './discount';
import { VisitPoint } from './visitPoint';

@Injectable()
export class DataService {

  private establishmentsUrl = 'http://localhost:8080/establishment';  // URL to web API
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

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

  getEstablishmentByUser(id: number): Promise<Establishment> {
    const url = `${this.establishmentsUrl}/${id}/getByUser`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Establishment)
      .catch(this.handleError);
  }

  create(establishment: Establishment): Promise<Establishment> {
    return this.http
      .post(this.establishmentsUrl, JSON.stringify(establishment), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as Establishment)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.establishmentsUrl}/${id}`;
    return this.http.delete(url, { headers: this.headers })
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(establishment: Establishment): Promise<Establishment> {
    return this.http
      .put(this.establishmentsUrl, JSON.stringify(establishment), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as Establishment)
      .catch(this.handleError);
  }

  getDiscounts(establishmentId: number): Promise<Discount[]> {
    return this.http
      .get(`http://localhost:8080/establishment/${establishmentId}/discounted`)
      .toPromise()
      .then(res => res.json() as Discount[])
      .catch(this.handleError);
  }

  takeDiscount(discountId: number, userId: number): Promise<Discount[]> {
    return this.http
      .post(`http://localhost:8080/discount/${discountId}/take/${userId}`, JSON.stringify('discount'), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as Discount[])
      .catch(this.handleError);
  }

  checkIfAdmin(establishmentId: number, userId: number): Promise<Establishment> {
    return this.http
      .get(`http://localhost:8080/establishment/${establishmentId}/isAdmin/${userId}`)
      .toPromise()
      .then(res => res.json() as Establishment)
      .catch(this.handleError);
  }

  getAdminEstablishment(userId: number): Promise<Establishment> {
    return this.http
      .get(`http://localhost:8080/establishment/getAdminEstablishment/${userId}`)
      .toPromise()
      .then(res => res.json() as Establishment)
      .catch(this.handleError);
  }

  attendanceReport(dates: string[], establishmentId: number): Promise<number> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/visitReport`,
        JSON.stringify(dates), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as number)
      .catch(this.handleError);
  }

  visitDaily(date: string, establishmentId: number): Promise<VisitPoint[]> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/visitDaily`,
        JSON.stringify(date), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as VisitPoint[])
      .catch(this.handleError);
  }

  visitWeekly(date: string, establishmentId: number): Promise<VisitPoint[]> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/visitWeekly`,
        JSON.stringify(date), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as VisitPoint[])
      .catch(this.handleError);
  }

  visitMonthly(date: string, establishmentId: number): Promise<VisitPoint[]> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/visitMonthly`,
        JSON.stringify(date), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as VisitPoint[])
      .catch(this.handleError);
  }

  profitReport(dates: string[], establishmentId: number): Promise<number> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/profitReport`,
        JSON.stringify(dates), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as number)
      .catch(this.handleError);
  }

  rateEstablishment(establishmentId: number, grade: string, user: number): Promise<Object> {
    return this.http
      .post(`http://localhost:8080/establishment/${establishmentId}/rating/${user}`,
        JSON.stringify(grade), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as Object)
      .catch(this.handleError);
  }

  rateHappening(happeningId: number, grade: string, user: number): Promise<Object> {
    return this.http
      .post(`http://localhost:8080/happening/${happeningId}/rating/${user}`,
        JSON.stringify(grade), { headers: this.headers })
      .toPromise()
      .then(res => res.json() as Object)
      .catch(this.handleError);
  }

  showAddress(address: string): Promise<any> {
    return this.http.get('https://maps.googleapis.com/maps/api/geocode/json?address=' + address)
      .toPromise()
      .then(res => res.json() as any)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

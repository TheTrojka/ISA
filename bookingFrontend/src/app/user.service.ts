import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { User } from './user';
import { Status } from './status';

@Injectable()
export class UserService {

  // private usersUrl = 'establishment/:establishmentId/user';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  register(user: User): Promise<User> {
    return this.http
      .post(`http://localhost:8080/register`, JSON.stringify(user), {headers : this.headers})
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }

  login(user: User): Promise<User> {
    return this.http
      .post(`http://localhost:8080/guests/login`, JSON.stringify(user), {headers : this.headers})
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }

  checkPassword(password: String, id: number): Promise<User> {
    const url = `http://localhost:8080/guests/${id}/checkPassword`;
    return this.http
      .post(url, password, {headers : this.headers})
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }

  confirm(token: string): Promise<any> {
    return this.http
      .get(`http://localhost:8080/confirm/${token}`, {headers : this.headers})
      .toPromise()
      .then(response => response.json() as Text)
      .catch(this.handleError);
  }

  getUser(id: number): Promise<User> {
    const url = `http://localhost:8080/guests/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }

  getUsers(): Promise<User[]> {
    const url = `http://localhost:8080/guests`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User[])
      .catch(this.handleError);
  }

  getFriends(id: number): Promise<User[]> {
    const url = `http://localhost:8080/guests/${id}/friends`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User[])
      .catch(this.handleError);
  }

  getAvailable(id: number): Promise<User[]> {
    const url = `http://localhost:8080/guests/${id}/friends/available`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User[])
      .catch(this.handleError);
  }

  getRequests(id: number): Promise<User[]> {
    const url = `http://localhost:8080/guests/${id}/requests`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User[])
      .catch(this.handleError);
  }

  addFriend(id: number, friend: number): Promise<any> {
    const url = `http://localhost:8080/guests/${id}/friends/add/${friend}`;
    return this.http.post(url, JSON.stringify('friend'), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as any)
      .catch(this.handleError);
  }

  acceptFriend(id: number, friend: number): Promise<any> {
    const url = `http://localhost:8080/guests/${id}/friends/accept/${friend}`;
    return this.http.post(url, JSON.stringify('friend'), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as any)
      .catch(this.handleError);
  }

  declineFriend(id: number, friend: number): Promise<any> {
    const url = `http://localhost:8080/guests/${id}/friends/decline/${friend}`;
    return this.http.delete(url)
      .toPromise()
      .then(response => response.json() as any)
      .catch(this.handleError);
  }

  deleteFriend(id: number, friend: number): Promise<any> {
    const url = `http://localhost:8080/guests/${id}/friends/delete/${friend}`;
    return this.http.delete(url)
      .toPromise()
      .then(response => response.json() as any)
      .catch(this.handleError);
  }

  update(user: User): Promise<User> {
    return this.http
      .put(`http://localhost:8080/guests`, JSON.stringify(user), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as User)
      .catch(this.handleError);
  }

  create(user: User, establishment: number): Promise<User> {    
    return this.http
      .post(`http://localhost:8080/guests/${establishment}`, JSON.stringify(user), {headers : this.headers})
      .toPromise()
      .then(res => res.json() as User)
      .catch(this.handleError);
  }

  invite(id: number, friend: number): Promise<any> {
    const url = `http://localhost:8080/visits/${id}/invite/${friend}`;
    return this.http.post(url, JSON.stringify('friend'), {headers: this.headers})
      .toPromise()
      .then(response => response.text())
      .catch(this.handleError);
  }

  getInvitation(id: number, friend: number): Promise<any> {
    const url = `http://localhost:8080/visits/${id}/invite/${friend}/invitation`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.text())
      .catch(this.handleError);
  }

  acceptInvitation(id: number, friend: number): Promise<any> {
    const url = `http://localhost:8080/visits/${id}/invite/${friend}/accept`;
    return this.http.post(url, JSON.stringify('friend'), {headers: this.headers})
      .toPromise()
      .then(response => response.text())
      .catch(this.handleError);
  }

  declineInvitation(id: number, friend: number): Promise<any> {
    const url = `http://localhost:8080/visits/${id}/invite/${friend}/decline`;
    return this.http.post(url, JSON.stringify('friend'), {headers: this.headers})
      .toPromise()
      .then(response => response.text())
      .catch(this.handleError);
  }

  cancelReservation(id: number): Promise<any> {
    const url = `http://localhost:8080/visits/${id}/cancel`;
    return this.http.post(url, JSON.stringify('friend'), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as any)
      .catch(this.handleError);
  }

  getStatus(): Promise<Status> {
    return this.http
      .get(`http://localhost:8080/guests/status`)
      .toPromise()
      .then(res => res.json() as Status)
      .catch(this.handleError);
  }

  setStatus(status: Status): Promise<Status> {
    return this.http
      .post(`http://localhost:8080/guests/status`, JSON.stringify(status), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Status)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { UrlService } from './url.service'
import { Person } from '../models/person'
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private loggedUser!: Person;
  private usersUrl: string;
  private formHeaders = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Content-Type':'application/json'});

  constructor(private http: HttpClient, private urlService: UrlService) {
    this.usersUrl = this.urlService.getUrl() + '/users/';
  }

  loginUser(username: string, password: string): Observable<Person> {
    console.log(this.usersUrl);
    if (username && password) {
      const queryParams = `?user=${username}&pass=${password}`;
      return this.http.put(this.usersUrl + queryParams,
        {headers: this.formHeaders, withCredentials:true}).pipe(
          map(resp => resp as Person)
      );
    } else {
      return this.http.get(this.usersUrl,
        {withCredentials:true}).pipe(
          map(resp => resp as Person)
        );
    }
  }

  logoutUser(): Observable<object> {
    return this.http.delete(this.usersUrl, {headers: this.regHeaders, withCredentials:true}).pipe();
  }

  updateUser(updatedUser: Person): Observable<object> {
    this.loggedUser = updatedUser;
    return this.http.put(this.usersUrl + this.loggedUser.id, updatedUser, 
      {headers: this.regHeaders, withCredentials:true}).pipe();
  }

  getLoggedUser(): Person {
    return JSON.parse(window.sessionStorage.user);
  }

  setLoggedUser(user: Person): void {
    window.sessionStorage.user = JSON.stringify(user);
  }

  registerUser(newUser: Person): Observable<Person> {
    return this.http.post(this.usersUrl, newUser, {headers: this.regHeaders, withCredentials:true}).pipe(
      map(resp => resp as Person)
    );
  }

  getUserById(userId: number): Observable<Person> {
    return this.http.get(this.usersUrl + '/id/' + userId, {withCredentials:true}).pipe(
      map(resp => resp as Person)      
    )
  }

  getUserByUsername(userUsername: string): Observable<Person> {
    return this.http.get(this.usersUrl + '/user/' + userUsername, {withCredentials:true}).pipe(
      map(resp => resp as Person)      
    )
  }
}

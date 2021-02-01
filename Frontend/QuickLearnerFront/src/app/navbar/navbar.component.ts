import { Component, EventEmitter, OnChanges, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router'
import { Person } from '../models/person'
import { PersonService } from '../services/person.service'
import { catchError } from 'rxjs/operators';
import { of, Subject, throwError } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnChanges {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  @Output() logOutEvent: EventEmitter<any> = new EventEmitter();
  loggedUser: Person | null | undefined;
  user!: string;
  pass!: string;
  regUser!: string;
  regPass!: string;
  registration: boolean = false;
  registered: boolean = true;
  isPassword: string = 'password';
  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit(): void {
    let alreadyLogged: Person = JSON.parse(window.sessionStorage.user);
    if (alreadyLogged) {
      this.user = alreadyLogged.username;
      this.pass = alreadyLogged.password;
      this.regUser = '';
      this.regPass = '';
      this.logIn();
    } else {
      this.user = '';
      this.pass = '';
      this.regUser = '';
      this.regPass = '';
    }
  }

  ngOnChanges() {
    console.log(this.user + ' ' + this.pass);
  }
  logIn() {
    //let invitedPerson: Person | null = await this.personServ.getUserByUsername(this.invitedUsername).pipe(catchError(err => {console.log(err); return of(null)})).toPromise();
    console.log(this.user + ' ' + this.pass);
    if (this.user && this.pass) {
      this.personService.loginUser(this.user, this.pass).pipe(catchError(err => {console.log(err); return of(null)})).subscribe(
        resp => {
          if(resp != null)
          {
            this.loggedUser = resp;
            window.sessionStorage.user = JSON.stringify(this.loggedUser);
            this.logInEvent.emit();
          }
          else{
            alert("Invalid Credentials! Please try again!");
          }
        }
      );
    }
  }

  logOut() {
    this.personService.logoutUser().subscribe(
      resp => {
        this.loggedUser = null;
        this.user = '';
        this.pass = '';

        window.sessionStorage.user = JSON.stringify(this.loggedUser);
        this.logOutEvent.emit();
        this.router.navigate(['home']);
      }
    );
  }

  toggleRegister() {
    this.regUser = '';
    this.regPass = '';
    this.registration = !this.registration;
  }

  registerUser() {
    let newUser:Person = new Person();
    newUser.username = this.regUser;
    newUser.password = this.regPass;

    if (newUser.username && newUser.password) {
      this.personService.registerUser(newUser).subscribe(
        resp => {
          if (resp) {
            this.registered = true;
            this.loggedUser = resp;
            this.regUser = '';
            this.regPass = '';
            this.user = resp.username;
            this.pass = resp.password;
            this.personService.setLoggedUser(this.loggedUser);
            this.logIn();
            this.registration = !this.registration;
          } else {
            this.registered = false;
          }
        }
      )
    }
  }

  togglePassword() {
    if (this.isPassword === 'password') {
      this.isPassword = 'text';
    } else {
      this.isPassword = 'password';
    }
  }

}

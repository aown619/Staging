import { Component } from '@angular/core';
import { Game } from './models/game';
import { Person } from './models/person';
import { PersonService } from './services/person.service';
import { Invite } from './models/invite';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BattleSquidsFrontApp';
  person: Person | null;
  game!: Game;
  constructor( private router: Router) {
    this.person = null;
  }

  setLogin() {
    this.person = window.sessionStorage.user;
  }

  setLogout() 
  {
    this.person = null;
  }
}

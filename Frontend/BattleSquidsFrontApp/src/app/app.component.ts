import { Component } from '@angular/core';
import { Game } from './models/game';
import { Person } from './models/person';
import { GameService } from './services/game.service';
import { GamestatusService } from './services/gamestatus.service';
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
  personServ: PersonService;
  gameServ: GameService;
  gameStatServ: GamestatusService;
  person: Person | null;
  game!: Game;
  constructor(personServ: PersonService, gameServ: GameService, gameStatServ: GamestatusService, private router: Router) {
    this.personServ = personServ;
    this.gameServ = gameServ;
    this.gameStatServ = gameStatServ;
    this.person = null;
  }

  setLogin() {
    this.person = window.sessionStorage.user;
  }

  setLogout() 
  {
    this.person = null;
  }

  async createGame()
  {
    this.game = new Game();
    this.game.player1 = this.personServ.getLoggedUser();
    this.game.player2 = null;
    this.game.activePlayerId = this.game.player1.id;
    this.game.board1 = null;
    this.game.board2 = null;

    this.game.status = await this.gameStatServ.getGameStatusById(1).toPromise();
    this.game.id = await this.gameServ.addGame(this.game).toPromise();

    window.sessionStorage.setItem("game", JSON.stringify(this.game));
  }

  async startGame(invite: any)
  {
    this.game = invite.game;
    this.game.player2 = this.personServ.getLoggedUser();
    await this.gameServ.updateGame(this.game).toPromise();
    window.sessionStorage.setItem("game", JSON.stringify(this.game));
    this.router.navigate(['gamescreen']);
  }
}

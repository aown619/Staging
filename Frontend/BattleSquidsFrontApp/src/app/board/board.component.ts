import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, Subscription } from 'rxjs';
import { Board } from '../models/board';
import { Game } from '../models/game';
import { MatchHistory } from '../models/matchhistory';
import { Person } from '../models/person';
import { Tile } from '../models/tile';
import { GameService } from '../services/game.service';
import { GamestatusService } from '../services/gamestatus.service';
import { MatchhistoryService } from '../services/matchhistory.service';
import { PersonService } from '../services/person.service';
import { SquidService } from '../services/squid.service';
import { TileService } from '../services/tile.service';
import { TilestatusService } from '../services/tilestatus.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent {
  @Input() player!: Person| null;
  @Input() board!: Board | null;
  @Input() initEvent!: Observable<void>;
  //private eventsSubscription!: Subscription;
  game!: Game;
  ready: boolean = false;
  primary!: boolean;
  squidSelect: number = -1;
  verticalPlacement: boolean = true
  imagePaths: string[][];
  verticalTiles: boolean[][];
  placedSquids: boolean[] = [false, false, false, false, false];
  lockout: boolean = false;
  enemySquids: number = 0;
  enemyReady: boolean = false;
  inkSound: string = '../../assets/inksound.wav';
  hitSound: string = '../../assets/hitsound.wav';
  enemiesHit: number = 0;
  alliesHit: number = 0;

  @Output() readyEvent = new EventEmitter<boolean>();


  sideArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  constructor(private router: Router, private tileServ: TileService, private tileStatServ: TilestatusService, private personServ: PersonService,private matchHistoryServ: MatchhistoryService, private squidServ: SquidService, private gameServ: GameService, private gameStatServ: GamestatusService)
  {
    //tileServ.openTileWebSocket(this, personServ.getLoggedUser().id);
    this.imagePaths = new Array<Array<string>>(10);
    this.verticalTiles = new Array<Array<boolean>>(10);
    for(let i = 0; i < 10; i++)
    {
      this.imagePaths[i] = new Array<string>(10);
      this.verticalTiles[i] = new Array<boolean>(10);
    }
    for(let i = 0; i < 10; i++)
    {
      for(let j = 0; j < 10; j++)
      {
        this.imagePaths[i][j] = "../../assets/tile_empty.png";
        this.verticalTiles[i][j] = false;
      }
    }
  }
  

  async selectSquid(squidId: number)
  {
    if (this.placedSquids[squidId-1])
    {
      alert("squid already placed!, please choose a different squid!");
      this.squidSelect = -1;
      return;
    }
    if(squidId == 1)
    {
      this.squidSelect= 1;
    }
    if(squidId == 2)
    {
      this.squidSelect= 2;
    }
    if(squidId == 3)
    {
      this.squidSelect= 3;
    }
    if(squidId == 4)
    {
      this.squidSelect= 4;
    }
    if(squidId == 5)
    {
      this.squidSelect= 5;
    }
  }

  changePlacementOrientation(horizontal: boolean)
  {
    if (horizontal)
    {
      this.verticalPlacement = false;
    }
    else{
      this.verticalPlacement = true;
    }
  }

  //place is 1 indexed
  getAssetFromSquidId(squidId: number, place: number): string
  {
    if(squidId == 1 && place == 1)
    {
      return "../../assets/tile_cuttle_1.png"
    }
    else if(squidId == 1 && place == 2)
    {
      return "../../assets/tile_cuttle_2.png"
    }
    else if(squidId == 2 && place == 1)
    {
      return "../../assets/tile_vampire_1.png"
    }
    else if(squidId == 2 && place == 2)
    {
      return "../../assets/tile_vampire_2.png"
    }
    else if(squidId == 2 && place == 3)
    {
      return "../../assets/tile_vampire_3.png"
    }
    else if(squidId == 3 && place == 1)
    {
      return "../../assets/tile_humboldt_1.png"
    }
    else if(squidId == 3 && place == 2)
    {
      return "../../assets/tile_humboldt_2.png"
    }
    else if(squidId == 3 && place == 3)
    {
      return "../../assets/tile_humboldt_3.png"
    }
    else if(squidId == 4 && place == 1)
    {
      return "../../assets/tile_giant_1.png"
    }
    else if(squidId == 4 && place == 2)
    {
      return "../../assets/tile_giant_2.png"
    }
    else if(squidId == 4 && place == 3)
    {
      return "../../assets/tile_giant_3.png"
    }
    else if(squidId == 4 && place == 4)
    {
      return "../../assets/tile_giant_4.png"
    }
    else if(squidId == 5 && place == 1)
    {
      return "../../assets/tile_kraken_1.png"
    }
    else if(squidId == 5 && place == 2)
    {
      return "../../assets/tile_kraken_2.png"
    }
    else if(squidId == 5 && place == 3)
    {
      return "../../assets/tile_kraken_3.png"
    }
    else if(squidId == 5 && place == 4)
    {
      return "../../assets/tile_kraken_4.png"
    }
    else if(squidId == 5 && place == 5)
    {
      return "../../assets/tile_kraken_5.png"
    }

    return "../../assets/tile_empty.png";
  }

  async placeSquid(x: number, y: number)
  {
    if(this.ready || this.squidSelect == -1)
    {
      return;
    }

    if(this.placedSquids[this.squidSelect-1])
    {
      alert("squid has already been placed!");
      return;
    }

    if(this.board == null)
    {
      console.log("board is null!");
      return;
    }
    
    let squidToPlace = await this.squidServ.getSquidById(this.squidSelect).toPromise();
    let realx: number = x-1;
    let realy: number = y-1;
    //placed squid from bottom up
    if(this.verticalPlacement)
    {
      if(realy < squidToPlace.size-1)
      {
        alert("not enough room!")
        return;
      }

      //ensure no squid conflicts
      for(let i = realy; i > realy-squidToPlace.size; i--)
      {
        if(this.board.tiles[realx][i].calamari.id != 6)
        {
          alert("a squid is already there!");
          return;
        }
      }
      this.lockout = true;

      //place squid
      for(let i = realy; i > realy-squidToPlace.size; i--)
      {
        this.board.tiles[realx][i].calamari = squidToPlace;
        await this.tileServ.updateTile(this.board.tiles[realx][i]).toPromise();
        this.tileServ.sendTile(this.board.tiles[realx][i]);
      }

      //update image
      for(let i = realy, j = squidToPlace.size; i > realy-squidToPlace.size; i--, j--)
      {
        this.imagePaths[realx][i] = this.getAssetFromSquidId(squidToPlace.id,j);
      }

      this.lockout = false;

    }
    if(!this.verticalPlacement)
    {
      if(realx > 9 - squidToPlace.size+1)
      {
        alert("not enough room!")
        return;
      }

      for(let i = realx; i < realx + squidToPlace.size; i++)
      {
        if(this.board.tiles[i][realy].calamari.id != 6)
        {
          alert("a squid is already there!");
          return;
        }
      }

      this.lockout = true;
      //place squid
      for(let i = realx; i < realx + squidToPlace.size; i++)
      {
        this.board.tiles[i][realy].calamari = squidToPlace;
        await this.tileServ.updateTile(this.board.tiles[i][realy]).toPromise();
        this.tileServ.sendTile(this.board.tiles[i][realy]);
      }

      //update image
      for(let i = realx, j = squidToPlace.size; i < realx + squidToPlace.size; i++, j--)
      {
        this.imagePaths[i][realy] = this.getAssetFromSquidId(squidToPlace.id,j);
        this.verticalTiles[i][realy] = true;
      }
      
      this.lockout = false;

    }

    this.placedSquids[this.squidSelect-1] = true;

    //check if all squids are placed
    for(let i = 0; i < this.placedSquids.length; i++)
    {
      if(!this.placedSquids[i])
      {
        return;
      }
    }

    this.ready = true;
    this.readyEvent.emit(true);

  }

  async initBoard(isBoard1: boolean)
  {
    this.game = JSON.parse(window.sessionStorage.getItem("game") as string);
    if(isBoard1)
    {
      this.board = this.game.board1;
    }
    else{
      this.board = this.game.board2;
    }

    if(this.board?.owner.id == this.personServ.getLoggedUser().id)
    {
      this.primary = true;
    }
    else{
      this.primary = false;
    }

  }

  async inkTile(x: number,y: number)
  {
    if (!this.ready)
    {
      return;
    }
    if(!this.enemyReady)
    {
      alert("enemy not ready!")
      return;
    }
    let tile: Tile;
    this.game = JSON.parse(window.sessionStorage.getItem("game") as string);
    if (this.board)
    {
      tile = this.board.tiles[x-1][y-1];
      if (this.game.activePlayerId != this.personServ.getLoggedUser().id)
      {
        alert("not your turn!");
        return;
      }
      if(this.personServ.getLoggedUser().id == this.board.owner.id)
      {
        alert("cant fire on your own squids");
        return;
      }
      if (tile.status.id == 1)
      {
        tile.status = await this.tileStatServ.getTileStatusById(2).toPromise();
        if(tile.calamari.id == 6)
        {
          this.imagePaths[x-1][y-1] = this.imagePaths[x-1][y-1].substr(0,this.imagePaths[x-1][y-1].length-4) +"_inked.png";
          this.playSound(this.inkSound);
        }
        else{
          this.imagePaths[x-1][y-1] = "../../assets/tile_hit.png"
          this.playSound(this.hitSound);
          this.enemiesHit++;
          if(this.enemiesHit >= 17)
          {
            this.win();
          }
        }
        this.tileServ.sendTile(tile);
        if(this.personServ.getLoggedUser().id == this.game.player1.id && this.game.player2)
        {
          this.game.activePlayerId = this.game.player2.id;
        }
        else{
          this.game.activePlayerId = this.game.player1.id;
        }
        window.sessionStorage.setItem("game", JSON.stringify(this.game));
      }
      else{
        alert("Tile has already been inked!");
      }
    }
    
  }

  async win()
  {
    let winHistory: MatchHistory = new MatchHistory();
    winHistory.end = new Date();
    winHistory.begin = new Date(window.sessionStorage.getItem("start") as string);
    
    winHistory.winner = this.personServ.getLoggedUser()

    if(winHistory.winner.id == this.game.player1.id && this.game.player2)
    {
      winHistory.loser = this.game.player2;
    }
    else{
      winHistory.loser = this.game.player1;
    }

    await this.matchHistoryServ.addMatchHistory(winHistory).toPromise();

    this.game = await this.gameServ.getGameById(this.game.id).toPromise();
    this.game.status = await this.gameStatServ.getGameStatusById(4).toPromise();
    await this.gameServ.updateGame(this.game).toPromise();
    alert("You Won!");
    let person = window.sessionStorage.getItem("user");
    window.sessionStorage.clear();
    window.sessionStorage.setItem("user",person as string);
    //this.personServ.logoutUser();
    this.router.navigate([''])
    this.tileServ.closeTileWebSocket();
  }

  lose()
  {
    alert("You Lose!");
    let person = window.sessionStorage.getItem("user");
    window.sessionStorage.clear();
    window.sessionStorage.setItem("user",person as string);
    //this.personServ.logoutUser();
    this.router.navigate(['']);
    this.tileServ.closeTileWebSocket();
  }

  updateTile(tile: Tile)
  {
    if(this.board && tile.boardId == this.board.id)
    {
      this.board.tiles[tile.x][tile.y] = tile;
      if(tile.status.id == 2)
      {
        this.imagePaths[tile.x][tile.y] = this.imagePaths[tile.x][tile.y].substr(0,this.imagePaths[tile.x][tile.y].length-4) +"_inked.png";
        if (tile.calamari.id === 6) {
          this.playSound(this.inkSound);
        } else {
          this.playSound(this.hitSound);
        }
        this.game.activePlayerId = this.personServ.getLoggedUser().id;
        window.sessionStorage.setItem("game", JSON.stringify(this.game));
        if(tile.calamari.id != 6)
        {
          this.alliesHit++;
          if(this.alliesHit >= 17)
          {
            this.lose();
          }
        }
      }
      else{
        this.enemySquids++;
        console.log(this.board?.id + "recorded" + this.enemySquids +  "enemy squids");
        if(this.enemySquids >= 17)
        {
          this.enemyReady = true;
          alert("enemy is ready!");
        }
      }
    }
  }

  playSound(link: string) {
    let sound = new Audio();
    sound.src = link;
    sound.load();
    sound.play();
  }

  getTileClass(xPos: number, yPos: number): string {
    if (this.board?.tiles[xPos-1][yPos-1].calamari.id === 6) return 'sprite';
    if (this.verticalTiles[xPos - 1][yPos - 1]) {
      return 'rotatedSprite';
    } else {
      return 'sprite';
    }
  }
}

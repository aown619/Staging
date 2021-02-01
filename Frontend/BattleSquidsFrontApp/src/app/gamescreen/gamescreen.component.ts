import { Component, OnInit, Output, ViewChild, EventEmitter } from '@angular/core';
import { GameService } from '../services/game.service';
import { PersonService } from '../services/person.service';
import { Game } from '../models/game';
import { GamestatusService } from '../services/gamestatus.service';
import { Person } from '../models/person'
import { Invite } from '../models/invite'
import { InviteService } from '../services/invite.service';
import { InviteStatusService } from '../services/inviteStatus.service';
import { InviteTypeService } from '../services/inviteType.service';
import { catchError } from 'rxjs/operators';
import { of, Subject, throwError } from 'rxjs';
import { Board } from '../models/board';
import { Tile } from '../models/tile';
import { BoardService } from '../services/board.service';
import { TilestatusService } from '../services/tilestatus.service';
import { SquidService } from '../services/squid.service';
import { ThrowStmt } from '@angular/compiler';
import { BoardComponent } from '../board/board.component';
import { ClientService } from '../services/client.service';
import { TileService } from '../services/tile.service';

@Component({
  selector: 'app-gamescreen',
  templateUrl: './gamescreen.component.html',
  styleUrls: ['./gamescreen.component.css']
})
export class GamescreenComponent implements OnInit {
  game! : Game;
  invitedUsername: string = "";
  invite: Invite | null = null;
  invited = false;
  started = false;
  sent = false;
  board1: Board;
  board2: Board;
  initEvent: Subject<void> = new Subject<void>();
  loading!: boolean;
  opponent!: Person | null;
  isSender!: boolean;
  @ViewChild('boardcomp1') boardComponent1!: BoardComponent;
  @ViewChild('boardcomp2') boardComponent2!: BoardComponent;


  //firt create an empty game, 1 player no boards. Once an invite is accepted boards will be filled
  constructor(private clientServ: ClientService, private personServ: PersonService,private tileServ: TileService , private inviteServ: InviteService, private inviteStatusServ: InviteStatusService, private inviteTypeServ: InviteTypeService, private boardserv: BoardService, private tilseStatServ: TilestatusService, private squidServ: SquidService, private gameServ: GameService) {
    this.fillGame();
    this.board1 = new Board();
    this.board2 = new Board();
   }

  ngOnInit(): void {
  }

  /*
  updateBoards()
  {
    console.log("Passing board");
    this.initEvent.next();
  }
  */

  async fillGame()
  {
    if(window.sessionStorage.getItem("game") != null)
    {
      let json = window.sessionStorage.getItem("game");
      json = this.remove_non_ascii(json as string) as string;
      this.game = await JSON.parse(json);
      if(this.game?.player2)
      {
        this.isSender = false;
        this.loading = true;
        this.opponent = this.game.player1;
        this.invited = true;
        this.clientServ.openClientWebsocket(this, this.personServ.getLoggedUser().id);
      }
    }
    else{
      console.log("moved too fast, taking a second to retry");
      setTimeout(() => {
        this.fillGame();
      }, 2000);
    }
  }

  async updateTile(tile: Tile)
  {
    if(tile.boardId == this.game.board1?.id)
    {
      this.boardComponent1.updateTile(tile);
    }
    else{
      this.boardComponent2.updateTile(tile);
    }
  }

  async onMessage(message: string)
  {
    //console.log(message);
    if(message == "loading done")
    {
      this.game = await this.gameServ.getGameById(this.game.id).toPromise();
      window.sessionStorage.setItem("game", JSON.stringify(this.game));
      this.startGame();
      this.clientServ.closeClientWebSocket();
      this.tileServ.openTileWebSocket(this, this.personServ.getLoggedUser().id);
      setTimeout(() => {
        this.boardComponent1.initBoard(true);
        this.boardComponent2.initBoard(false);
        
      }, 2000);
    }
  }

  readyBoards(readyStatus: boolean)
  {
    
    if(this.personServ.getLoggedUser().id == this.board1.owner.id)
    {
      this.boardComponent2.ready = readyStatus;
      this.boardComponent1.enemyReady = readyStatus;
    }
    else{
      this.boardComponent1.ready = readyStatus;
      this.boardComponent2.enemyReady = readyStatus;
    }
   
  }

  async createBoards()
  {
    if(this.game.player2 != null)
    {
      this.board1.owner = this.game.player1;
      this.board2.owner = this.game.player2;

      this.board1.gameId = this.game.id;
      this.board2.gameId = this.game.id;
      let arr1 = new Array<Array<Tile>>(10);
      let arr2 = new Array<Array<Tile>>(10);

      for(let i = 0; i < 10; i++)
      {
        arr1[i] = new Array<Tile>(10);
        arr2[i] = new Array<Tile>(10);
      }

      let emptySquid = await this.squidServ.getSquidById(6).toPromise();
      let hiddenTileStatus = await this.tilseStatServ.getTileStatusById(1).toPromise();

      for(let i = 0; i < 10; i++)
      {
        for(let j = 0; j < 10; j++)
        {
          let tileToAdd = new Tile();
          tileToAdd.calamari = emptySquid;
          tileToAdd.status = hiddenTileStatus
          tileToAdd.x = i;
          tileToAdd.y = j;
          arr1[i][j] = tileToAdd;
          arr2[i][j] = tileToAdd;
        }
      }
      this.board1.tiles = arr1;
      this.board2.tiles = arr2;

      await this.boardserv.addBoard(this.board1).toPromise();
      await this.boardserv.addBoard(this.board2).toPromise();

      this.game = await this.gameServ.getGameById(this.game.id).toPromise();
      this.board1 = this.game.board1 as Board;
      this.board2 = this.game.board2 as Board;
      window.sessionStorage.setItem("game", JSON.stringify(this.game));
      //console.log("game stored in session");
      //console.log(window.sessionStorage.getItem("game"));
    }
    this.startGame();
    this.clientServ.sendMessage("loading done=" + this.game.player2?.id);
    this.tileServ.openTileWebSocket(this, this.personServ.getLoggedUser().id);
    setTimeout(() => {
      this.boardComponent1.initBoard(true);
      this.boardComponent2.initBoard(false);
      
    }, 2000);
    //this.updateBoards();

    
  }

  remove_non_ascii(str: string) {
  
    if ((str===null) || (str===''))
         return false;
   else
     str = str.toString();
    
    return str.replace(/[^\x20-\x7E]/g, '');
  }

  async sendInvite(): Promise<void>
  {

    let invitedPerson: Person | null = await this.personServ.getUserByUsername(this.invitedUsername).pipe(catchError(err => {console.log(err); return of(null)})).toPromise();
    //console.log(invitedPerson);
    if (invitedPerson)
    {
      if (invitedPerson == this.personServ.getLoggedUser())
      {
        alert("You cannot invite yourself!");
      }
      else
      {
        let newInvite = new Invite();
        newInvite.sender = this.personServ.getLoggedUser();
        newInvite.game = JSON.parse(window.sessionStorage.game);
        //console.log(JSON.parse(window.sessionStorage.game));
        newInvite.receiver = invitedPerson;
        newInvite.status = await this.inviteStatusServ.getInviteStatusById(1).toPromise();
        newInvite.type = await this.inviteTypeServ.getInviteTypeById(1).toPromise();
        newInvite.id = await this.inviteServ.addInvite(newInvite).toPromise();
        this.inviteServ.openInviteWebSocket(this.personServ.getLoggedUser().id, this)
        this.invited = true;
        this.invite = newInvite;
        this.sent = true;
      }
    }
    else{
      alert("No user " + this.invitedUsername + " found!");
    }
  }

  async cancelInvite()
  {
    if (this.invite != null)
    {
      await this.inviteServ.deleteInvite(this.invite.id).toPromise();
    }
    this.invite = null;
    this.invited = false;
    this.inviteServ.closeInviteWebSocket();
  }

  startGame()
  {
    this.loading = false;
    this.started = true;
    window.sessionStorage.setItem("start",new Date().toJSON());
    
  }

  readInvite(str: string)
  {
    if(str == "accepted")
    {
      if(this.game != null && this.invite != null)
      {
        this.game.player2 = this.invite.receiver;
        this.opponent = this.game.player2;
        this.isSender = true;
        this.loading = true;
        this.inviteServ.closeInviteWebSocket();
        this.clientServ.openClientWebsocket(this, this.personServ.getLoggedUser().id);
        this.createBoards();
        //this.startGame();
      }
    }
    else if(str == "rejected")
    {
      alert("Invite Declined! Please invite someone else!")
      this.cancelInvite();
    }
    console.log(str);
  }
  
}
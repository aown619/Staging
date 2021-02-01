import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tile } from '../models/tile';
import { TileStatus } from '../models/tilestatus';
import { Squid } from '../models/squid';
import { BoardComponent } from '../board/board.component';
import { GamescreenComponent } from '../gamescreen/gamescreen.component';
@Injectable({
  providedIn: 'root'
})
export class TileService implements OnDestroy {
  webSocket!: WebSocket;
  tile!: Tile;
  url: string;

  constructor(private http: HttpClient, private urlServ: UrlService) { this.url = urlServ.getUrl() + '/tile'; };
 
  addTile(tile: Tile): Observable<number> {
    return this.http.post(this.url, tile).pipe(map(resp => resp as number));
  }

  getTileById(tileId: number): Observable<Tile> {
    return this.http.get(this.url + `/${tileId}`).pipe(map(resp => resp as Tile));
  }

  getTileByBoardIdAndXY(boardId: number, x: number, y: number): Observable<Tile> {
    return this.http.get(this.url + `/xy/?board=${boardId}&x=${x}&y=${y}`).pipe(map(resp => resp as Tile));
  }

  getTileByBoardIdAndX(boardId: number, x: number): Observable<Tile[]> {
    return this.http.get(this.url + `/x/?board=${boardId}&x=${x}`).pipe(map(resp => resp as Tile[]));
  }

  getTileByBoardIdAndY(boardId: number, y: number): Observable<Tile[]> {
    return this.http.get(this.url + `/y/?board=${boardId}&y=${y}`).pipe(map(resp => resp as Tile[]));
  }

  getTileByBoardId(boardId: number): Observable<Tile[]> {
    return this.http.get(this.url + `/board/?board=${boardId}`).pipe(map(resp => resp as Tile[]));
  }

  getTileByBoardIdAndStatus(boardId: number, status: TileStatus): Observable<Tile[]> {
    return this.http.get(this.url + `/status/?board=${boardId}&status=${status}`).pipe(map(resp => resp as Tile[]));
  }

  getTileByBoardIdAndSquid(boardId: number, squid: Squid): Observable<Tile[]> {
    return this.http.get(this.url + `/squid/?board=${boardId}&squid=${squid}`).pipe(map(resp => resp as Tile[]));
  }

  updateTile(tile: Tile): Observable<object> {
    return this.http.put(this.url + "/" + tile.id, tile).pipe();
  }

  deleteTile(tileId: number): Observable<object> {
    return this.http.delete(this.url + `/${tileId}`).pipe();
  }

  // Websocket code

  public openTileWebSocket(gamescreen: GamescreenComponent, personId: number) {
    this.webSocket = new WebSocket('ws://localhost:8080/BattleSquids/tileaction?persid=' + personId);

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event);
    };

    this.webSocket.onmessage = (event) => {
      let tile = JSON.parse(event.data);
      gamescreen.updateTile(tile);
      console.log(tile);
    };

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event);
    };
  }

  public sendTile(tile: Tile) {
    this.webSocket.send(JSON.stringify(tile));
  }

  public closeTileWebSocket() {
    this.webSocket.close()
  }

  public ngOnDestroy() {
    this.webSocket.close();
  }
}

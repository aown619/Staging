import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Game } from '../models/game';
import { GameStatus } from '../models/gamestatus';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class GamestatusService {

  url : string;
  
  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = urlService.getUrl() + "/game/status";
   }

   getAllGameStatus(): Observable<GameStatus[]>
   {
    return this.http.get(this.url).pipe(map(resp => resp as GameStatus[]));
   }

   addGameStatus(gamestatus: GameStatus):  Observable<number>
   {
     return this.http.post(this.url, gamestatus).pipe(map(resp => resp as number));
   }

   getGameStatusById(id: number): Observable<GameStatus>
   {
     return this.http.get(this.url + "/" + id).pipe(map(resp => resp as GameStatus));
   }

   updateGameStatus(gamestatus: GameStatus): Observable<object>
   {
     return this.http.put(this.url, gamestatus).pipe();
   }

   deleteGameStatus(id: number): Observable<object>
   {
     return this.http.delete(this.url + "/" + id).pipe();
   }
  }
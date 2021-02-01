import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Game } from '../models/game';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  url : string;
  
  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = urlService.getUrl() + "/game";
   }

   getAllGames(): Observable<Game[]>
   {
    return this.http.get(this.url).pipe(map(resp => resp as Game[]));
   }

   addGame(game: Game):  Observable<number>
   {
     return this.http.post(this.url, game).pipe(map(resp => resp as number));
   }

   getGameById(id: number): Observable<Game>
   {
     return this.http.get(this.url + "/" + id).pipe(map(resp => resp as Game));
   }

   updateGame(game: Game): Observable<object>
   {
     return this.http.put(this.url, game).pipe();
   }

   deleteGame(id: number): Observable<object>
   {
     return this.http.delete(this.url + "/" + id).pipe();
   }
}
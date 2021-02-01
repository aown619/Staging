import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { Observable } from 'rxjs';
import { Board } from '../models/board'
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BoardService {
  url: string;

  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = urlService.getUrl() + "/board";
   }

   getAllBoard(): Observable<Board[]>
   {
    return this.http.get(this.url).pipe(map(resp => resp as Board[]));
   }

   addBoard(Board: Board):  Observable<number>
   {
     return this.http.post(this.url, Board).pipe(map(resp => resp as number));
   }

   getBoardById(id: number): Observable<Board>
   {
     return this.http.get(this.url + "/" + id).pipe(map(resp => resp as Board));
   }

   updateBoard(Board: Board): Observable<object>
   {
     return this.http.put(this.url, Board).pipe();
   }

   deleteBoard(id: number): Observable<object>
   {
     return this.http.delete(this.url + "/" + id).pipe();
   }
}

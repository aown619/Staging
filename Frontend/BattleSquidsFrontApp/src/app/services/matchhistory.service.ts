import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { Observable } from 'rxjs';
import { MatchHistory } from '../models/matchhistory'
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MatchhistoryService {
  url: string;

  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = urlService.getUrl() + "/matchhistory";
   }

   getAllMatchHistory(): Observable<MatchHistory[]>
   {
    return this.http.get(this.url).pipe(map(resp => resp as MatchHistory[]));
   }

   getMyMatchHistory(userId: number): Observable<MatchHistory[]>
   {
     return this.http.get(this.url + `/user/${userId}`).pipe(map(resp => resp as MatchHistory[]));
   }

   addMatchHistory(MatchHistory: MatchHistory):  Observable<number>
   {
     return this.http.post(this.url, MatchHistory).pipe(map(resp => resp as number));
   }

   getMatchHistoryById(id: number): Observable<MatchHistory>
   {
     return this.http.get(this.url + "/" + id).pipe(map(resp => resp as MatchHistory));
   }

   updateMatchHistory(MatchHistory: MatchHistory): Observable<object>
   {
     return this.http.put(this.url, MatchHistory).pipe();
   }

   deleteMatchHistory(id: number): Observable<object>
   {
     return this.http.delete(this.url + "/" + id).pipe();
   }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { Observable } from 'rxjs';
import { Squid } from '../models/squid'
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SquidService {
  url: string;

  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = urlService.getUrl() + "/squid";
   }

   getAllSquid(): Observable<Squid[]>
   {
    return this.http.get(this.url).pipe(map(resp => resp as Squid[]));
   }

   addSquid(Squid: Squid):  Observable<number>
   {
     return this.http.post(this.url, Squid).pipe(map(resp => resp as number));
   }

   getSquidById(id: number): Observable<Squid>
   {
     return this.http.get(this.url + "/" + id).pipe(map(resp => resp as Squid));
   }

   updateSquid(Squid: Squid): Observable<object>
   {
     return this.http.put(this.url, Squid).pipe();
   }

   deleteSquid(id: number): Observable<object>
   {
     return this.http.delete(this.url + "/" + id).pipe();
   }
}

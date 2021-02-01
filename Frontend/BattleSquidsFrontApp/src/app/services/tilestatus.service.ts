import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { Observable } from 'rxjs';
import { TileStatus } from '../models/tilestatus'
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TilestatusService {
  url: string;

  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = urlService.getUrl() + "/tile/status";
   }

   getAllTileStatus(): Observable<TileStatus[]>
   {
    return this.http.get(this.url).pipe(map(resp => resp as TileStatus[]));
   }

   addTileStatus(tilestatus: TileStatus):  Observable<number>
   {
     return this.http.post(this.url, tilestatus).pipe(map(resp => resp as number));
   }

   getTileStatusById(id: number): Observable<TileStatus>
   {
     return this.http.get(this.url + "/" + id).pipe(map(resp => resp as TileStatus));
   }

   updateTileStatus(tilestatus: TileStatus): Observable<object>
   {
     return this.http.put(this.url, tilestatus).pipe();
   }

   deleteTileStatus(id: number): Observable<object>
   {
     return this.http.delete(this.url + "/" + id).pipe();
   }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UrlService } from './url.service'
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { InviteType } from '../models/inviteType';

@Injectable({
  providedIn: 'root'
})
export class InviteTypeService {
    url: string;

    constructor(private http: HttpClient, private urlService: UrlService) { 
        this.url = urlService.getUrl() + "/inviteTypes";
    }

    getInviteTypeById(id: number): Observable<InviteType>{
        return this.http.get(this.url + `/${id}`).pipe(map(resp => resp as InviteType));
    }

    //no need for these, since db is prepopulated:
    
    // addInviteType(InviteType: InviteType): Observable<number> {
    //     return this.http.post(this.url, InviteType).pipe(map(resp => resp as number));
    // }

    // updateInviteType(InviteType: InviteType): Observable<object> {
    //     return this.http.put(this.url, InviteType).pipe();
    // }

    // deleteBoard(id: number): Observable<object> {
    //     return this.http.delete(this.url + `/${id}`).pipe();
    // }
}
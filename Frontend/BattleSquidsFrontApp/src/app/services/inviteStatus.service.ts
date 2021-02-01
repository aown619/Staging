import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UrlService } from './url.service'
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { InviteStatus } from '../models/inviteStatus';

@Injectable({
  providedIn: 'root'
})
export class InviteStatusService {
    url: string;

    constructor(private http: HttpClient, private urlService: UrlService) { 
        this.url = urlService.getUrl() + "/inviteStatuses";
    }

    getInviteStatusById(id: number): Observable<InviteStatus>{
        return this.http.get(this.url + `/${id}`).pipe(map(resp => resp as InviteStatus));
    }

    //no need for these, since db is prepopulated:
    
    // addInviteStatus(InviteStatus: InviteStatus): Observable<number> {
    //     return this.http.post(this.url, InviteStatus).pipe(map(resp => resp as number));
    // }

    // updateInviteStatus(InviteStatus: InviteStatus): Observable<object> {
    //     return this.http.put(this.url, InviteStatus).pipe();
    // }

    // deleteBoard(id: number): Observable<object> {
    //     return this.http.delete(this.url + `/${id}`).pipe();
    // }
}
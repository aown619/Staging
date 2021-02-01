import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Chat } from '../models/chat';
import { UrlService } from '../url.service';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  url : string;
  
  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = urlService.getUrl() + "/chat";
   }

   getAllChat(): Observable<Chat[]>
   {
    return this.http.get(this.url).pipe(map(resp => resp as Chat[]));
   }

   addChat(chat: Chat):  Observable<number>
   {
     return this.http.post(this.url, chat).pipe(map(resp => resp as number));
   }

   getChatById(id: number): Observable<Chat>
   {
     return this.http.get(this.url + "/" + id).pipe(map(resp => resp as Chat));
   }

   updateChat(chat: Chat): Observable<object>
   {
     return this.http.put(this.url, chat).pipe();
   }

   deleteChat(id: number): Observable<object>
   {
     return this.http.delete(this.url + "/" + id).pipe();
   }
}

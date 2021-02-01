import { Injectable } from '@angular/core';
import { GamescreenComponent } from '../gamescreen/gamescreen.component';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  webSocket!: WebSocket
  constructor() { }


  public openClientWebsocket(gameScreen: any, personId: number)
  {
    this.webSocket = new WebSocket('ws://localhost:8080/BattleSquids/clientaction?persid=' + personId);

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event);
    };

    this.webSocket.onmessage = (event) => {
      //console.log("onmessage called");
      gameScreen.onMessage(event.data as string);
    };

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event);
    };
  }

  public closeClientWebSocket() {
    this.webSocket.close()
  }

  public sendMessage(message: string) {
    this.webSocket.send(message);
  }

}

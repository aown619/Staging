import { Component, OnDestroy, OnInit } from '@angular/core';
import { Game } from '../models/game';
import { Invite } from '../models/invite';
import { InviteService } from '../services/invite.service';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-invite',
  templateUrl: './invite.component.html',
  styleUrls: ['./invite.component.css']
})
export class InviteComponent implements OnInit, OnDestroy {
  
  constructor(
    private inviteServ:InviteService, 
    private personService:PersonService, 
    private activeGame:Game, 
    private selectedRecipientUsername: string,
    private selectedType: string
  ) { }

  ngOnInit(): void {
    this.inviteServ.openInviteWebSocket();
  }

  ngOnDestroy(): void {
    this.inviteServ.closeInviteWebSocket();
  }

  sendInvite(): void {
    let i: Invite = new Invite();
    i.sender = this.personService.getLoggedUser();
    i.receiver = this.personService.getUserByUsername(this.selectedRecipientUsername);
    i.game = this.activeGame;
    
    i.type.id = this.selectedType == "Player" ? 1 : 2;
    i.type.name = this.selectedType;
    
    i.status.id = 1;
    i.status.name = "Pending";

    console.log(i);
    this.inviteServ.sendInvite(i);
  }
}

import { Component, OnInit } from '@angular/core';
import { InviteService } from '../services/invite.service';
import { Invite } from '../models/invite'
import { PersonService } from '../services/person.service';
import { InviteStatusService } from '../services/inviteStatus.service';
import { Output, EventEmitter } from '@angular/core';
import { Person } from '../models/person';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {
  invites!: Invite[];
  visible: Boolean = true;
  opponent!: Person;

  constructor(private inviteServ: InviteService, private personServ: PersonService, private invStatServ: InviteStatusService) {
    this.inviteServ.getAllInvitesReceivedByUserWithId(this.personServ.getLoggedUser().id).subscribe(resp => this.invites = resp);
   }

  ngOnInit(): void {
  }

  @Output() startGameEvent = new EventEmitter<Invite>();

  async acceptInvite(i: number)
  {
    this.opponent = this.invites[i].sender;
    this.invites[i].status = await this.invStatServ.getInviteStatusById(2).toPromise();
    await this.inviteServ.updateInvite(this.invites[i]).toPromise();
    this.visible = false;
    this.startGameEvent.emit(this.invites[i]);
    console.log("emitted");
  }
  
  async declineInvite(i: number)
  {
    console.log("declined" + i)
    this.invites[i].status = await this.invStatServ.getInviteStatusById(3).toPromise();
    console.log(this.invites[i]);
    await this.inviteServ.updateInvite(this.invites[i]).toPromise();
    this.invites.splice(i,1);
  }

  checkAnswerableInvites(): boolean {
    if (!this.invites || this.invites === undefined || this.invites.length === 0) return false;
    for (let invite of this.invites) {
      console.log(invite);
      if (invite.status.id !== 1) return false;
    }
    return true;
  }
}

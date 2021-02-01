import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Invite } from '../../models/invite';
import { InviteService } from '../../services/invite.service';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-invite-list',
  templateUrl: './invite-list.component.html',
  styleUrls: ['./invite-list.component.css'],
  providers: [InviteService, PersonService]
})

export class InviteListComponent implements OnInit {
  @Input() pertainsToSent: boolean = false;
  @Input() list: Invite[] = [];

  constructor(
    private inviteService: InviteService,
    private personService: PersonService,
  ) { }

  ngOnInit(): void {
    // this.list = this.getInvites();
  }

  // getInvites(): Invite[] {
  //   let id: number = this.personService.getLoggedUser().id;
  //   return this.pertainsToSent ? 
  //     this.inviteService.getAllInvitesSentByUserWithId(id) : 
  //     this.inviteService.getAllInvitesReceivedByUserWithId(id);
  // }
}

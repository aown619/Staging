import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { MatchHistory } from '../models/matchhistory';
import { MatchhistoryService } from '../services/matchhistory.service';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-matchhistory',
  templateUrl: './matchhistory.component.html',
  styleUrls: ['./matchhistory.component.css']
})
export class MatchhistoryComponent implements OnInit {
  history!: MatchHistory[];

  constructor(private location: Location, private historyServ: MatchhistoryService, private personServ: PersonService) { 
    this.historyServ.getMyMatchHistory(this.personServ.getLoggedUser().id).subscribe(resp => this.formatMatchHistory(resp));
  }

  ngOnInit(): void {

  }

  formatMatchHistory(resp: MatchHistory[]): void {
    if (resp && resp !== undefined) {
      if (resp.length > 0) {
        resp.sort(function(a,b){
          let aBegin: Date = a.begin;
          let bBegin: Date = b.begin;
          if (aBegin < bBegin) { return -1; };
          if (aBegin > bBegin) { return 1; };
          return 0;
        });
        for (let match of resp) {
          let matchBegin = new Date(match.begin).toString();
          let matchEnd = new Date(match.end).toString(); 
          match.begin = new Date(matchBegin);
          match.end = new Date(matchEnd);
        }
      }
    }
    this.history = resp;
  }

  formatDate(date: Date): string {
    let gmtStart: number = date.toString().indexOf('GMT');
    return date.toString().substring(0,gmtStart - 1);
  }

  returnToPrevious(): void {
    this.location.back();
  }

  getWinOrLose(winner: string): string {
    if (winner === this.personServ.getLoggedUser().username) {
      return 'mh-winner';
    } else {
      return 'mh-loser';
    }
  }

  getDataWithUser(user: string): string {
    if (user === this.personServ.getLoggedUser().username) {
      return 'mh-data mh-current-user';
    } else {
      return 'mh-data';
    }
  }
}

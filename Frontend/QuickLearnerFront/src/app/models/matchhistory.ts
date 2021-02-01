import {Person} from "./person"

export class MatchHistory
{
    id: number;
    begin: Date;
    end: Date;
    winner: Person;
    loser: Person;

    constructor()
    {
        this.id = -1;
        this.begin = new Date();
        this.end = new Date();
        this.winner = new Person();
        this.loser = new Person();
    }
}
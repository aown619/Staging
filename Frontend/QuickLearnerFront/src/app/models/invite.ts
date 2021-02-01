import { Person } from "./person";
import { Game } from "./game";
import { InviteType } from "./inviteType";
import { InviteStatus } from "./inviteStatus";

export class Invite {
    id: number;
    sender: Person;
    receiver: Person;
    game: Game;
    type: InviteType;
    status: InviteStatus;

    constructor(){
        this.id = -1;
        this.sender = new Person();
        this.receiver = new Person();
        this.game = new Game();
        this.type = new InviteType();
        this.status = new InviteStatus();
    }
}
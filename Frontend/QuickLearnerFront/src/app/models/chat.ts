import{ Person } from "./person";


export class Chat{
    id: number;
    gameId: number;
    sender: Person;
    message: string;

    constructor(){
        this.id= -1;
        this.gameId= -1;
        this.message= "";
        this.sender = new Person();

    }

}

import { Board } from "./board";
import { Person } from "./person";
import { GameStatus } from "./gamestatus";



export class Game{

    id: number;
    player1: Person;
    player2 : Person | null;
    activePlayerId: number;
    status: GameStatus;
    board1: Board | null;
    board2 : Board | null;


    constructor(){
        this.id=-1;
        this.player1=new Person();
        this.player2= new Person();
        this.activePlayerId=-1;
        this.status=new GameStatus();
        this.board1= new Board();
        this.board2 = new Board();
    }
}
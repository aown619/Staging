import {Person} from "./person"
import {Tile} from "./tile"

export class Board
{
    id: number;
    owner: Person;
    gameId: number;
    tiles: Tile[][];

    constructor()
    {
        this.id = -1;
        this.owner = new Person();
        this.gameId = -1;
        this.tiles = [];
    }

}

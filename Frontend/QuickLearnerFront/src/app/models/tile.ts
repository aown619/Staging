import { Squid } from "./squid";
import { TileStatus } from "./tilestatus";

export class Tile {
    id: number;
    boardId: number;
    status: TileStatus;
    calamari: Squid;
    x: number;
    y: number;

    constructor () {
        this.id = -1;
        this.boardId = -1;
        this.status = new TileStatus();
        this.calamari = new Squid();
        this.x = -1;
        this.y = -1;
    }
}
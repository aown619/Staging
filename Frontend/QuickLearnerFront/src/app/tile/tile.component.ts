import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { TileService } from '../services/tile.service'
import { Tile } from '../models/tile'

@Component({
  selector: 'app-tile',
  templateUrl: './tile.component.html',
  styleUrls: ['./tile.component.css']
})
export class TileComponent implements OnInit, OnDestroy {
  @Input() tile!: Tile;

  constructor(private tileServ:TileService) { }

  ngOnInit(): void {
    //this.tileServ.openTileWebSocket();
  }

  ngOnDestroy(): void {
    //this.tileServ.closeTileWebSocket();
  }

  sendTile(): void {
    let t: Tile = new Tile();
    console.log(t);
    this.tileServ.sendTile(t);
  }
}

import { Component, Input, OnInit } from '@angular/core';
import { Person } from '../models/person';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit {
  @Input() loading!: boolean;
  @Input() opponent!: Person | null;
  @Input() isSender!: boolean;
  images: string[] = ['../../assets/loading_sender.gif', '../../assets/loading_receiver.gif'];

  constructor() { this.loading = false;}

  ngOnInit(): void {
  }

}

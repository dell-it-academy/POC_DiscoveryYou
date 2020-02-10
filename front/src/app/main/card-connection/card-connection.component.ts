import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-connection',
  templateUrl: './card-connection.component.html',
  styleUrls: ['./card-connection.component.css']
})
export class CardConnectionComponent implements OnInit {
  numberConnections: Number = 10;
  constructor() { }

  ngOnInit(): void {
  }

}

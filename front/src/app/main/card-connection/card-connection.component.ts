import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-connection',
  templateUrl: '../shared/template/card/card.html',
  styleUrls: ['../shared/template/card/card-connection.component.css']
})
export class CardConnectionComponent implements OnInit {
  numberCard: Number = 10;
  textCard: String = "Connections Number";
  constructor() { }

  ngOnInit(): void {
  }

}

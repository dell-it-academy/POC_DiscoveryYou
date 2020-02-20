import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-dashboard',
  templateUrl: './card-dashboard.component.html',
  styleUrls: ['./card-dashboard.component.css']
})
export class CardDashboardComponent implements OnInit {
  connections :Object = {numberCard:"10",textCard:"Number of connections"};
  constructor() { }

  ngOnInit(): void {
  }

}

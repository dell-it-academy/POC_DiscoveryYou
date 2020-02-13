import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-dashboard',
  templateUrl: './card-dashboard.component.html',
  styleUrls: ['./card-dashboard.component.css']
})
export class CardDashboardComponent implements OnInit {
  profileProperties :Object = {numberCard:"69",textCard:"Text Sample", routerLink:"GONNAWORKONIT"};
  constructor() { }

  ngOnInit(): void {
  }

}

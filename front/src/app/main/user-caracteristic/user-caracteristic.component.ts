import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-user-caracteristic',
  templateUrl: './user-caracteristic.component.html',
  styleUrls: ['./user-caracteristic.component.css']
})
export class UserCaracteristicComponent implements OnInit {
  @Input() userCaracteristics: Object;
  colors = ["red","black","blue"];

  constructor() { }

  ngOnInit(): void {
    console.log(this.userCaracteristics);
  }

}

import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-card-caracteristic',
  templateUrl: './card-caracteristic.component.html',
  styleUrls: ['./card-caracteristic.component.css']
})
export class CardCaracteristicComponent implements OnInit {
  @Input() name: String = "No one found";
  constructor() { }

  ngOnInit(): void {
    console.log(name)
  }

}

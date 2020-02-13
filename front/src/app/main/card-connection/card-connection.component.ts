import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-card-connection',
  templateUrl: './card-connection.component.html',
  styleUrls: ['./card-connection.component.css']
})
export class CardConnectionComponent implements OnInit {
  public numberCard: Number = 69;
  public textCard: String = "Standard text";
  public routerLink: String;
  @Input() propertiesCard: {numberCard,textCard, routerLink};
  constructor() { }

  ngOnInit(): void {
    this.numberCard = this.propertiesCard['numberCard'];
    this.textCard = this.propertiesCard['textCard'];
    this.routerLink = this.propertiesCard['routerLink'];
  }

}

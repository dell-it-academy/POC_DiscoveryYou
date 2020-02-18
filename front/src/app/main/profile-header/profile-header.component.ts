import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-profile-header',
  templateUrl: './profile-header.component.html',
  styleUrls: ['./profile-header.component.css']
})
export class ProfileHeaderComponent implements OnInit {
@Input() arrayName: String;
public firstName : String;
public lastName : String;
  constructor() { }
  
  ngOnInit(): void {
    this.firstName = this.arrayName[0];
    this.lastName = this.arrayName[1];
  }

}

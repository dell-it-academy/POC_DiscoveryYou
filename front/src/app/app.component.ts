import { Component, OnInit } from '@angular/core';
import {ProfileRequesterService} from './services/profile-requester.service'
import {ProfileModel} from './models//profile/profile-model'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Dell Discovery You - Matrix';
  currentProfile:ProfileModel;
  ngOnInit(){
    this.userService.getProfile("123").subscribe(
      res =>{
        this.currentProfile = res as any;

      }
    );

  }
  constructor(private userService:ProfileRequesterService){}
}

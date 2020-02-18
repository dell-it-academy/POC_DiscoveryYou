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
    this.userService.getProfile("1230422").subscribe(
      res =>{
        this.currentProfile = new ProfileModel();
        this.currentProfile.firstName = res['firstName'];
        this.currentProfile.lastName = res['lastName'];
        this.currentProfile.badge = res['badge'];
      }
    );
  }

  // sendName{


  // }
  constructor(private userService:ProfileRequesterService){}
}

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
  userSkills: Object;
  userInterests: Object;
  ngOnInit(){
    this.userService.getProfile("1230422").subscribe(
      res =>{
        this.currentProfile = new ProfileModel();
        this.currentProfile.firstName = res['firstName'];
        this.currentProfile.lastName = res['lastName'];
        this.currentProfile.badge = res['badge'];
        this.currentProfile.interests = res['interests'];
        this.currentProfile.skills = res['skills'];
        this.userInterests = {"name": "Interests","caracteristic":this.currentProfile.interests}
        this.userSkills = {"name": "Skills","caracteristic":this.currentProfile.skills}
      }
    );
  }

  // sendName{


  // }
  constructor(private userService:ProfileRequesterService){}
}

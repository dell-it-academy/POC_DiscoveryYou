import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ProfileRequesterService {
  private standardUrl = "TYPEHERE";
  constructor(private httpProfile:HttpClient) { }

  getProfile(badge:String){
    let headers = new HttpHeaders();
    /*
      I need some backend guy here haha
    */
    return this.httpProfile.get(this.standardUrl,{headers});
  }
}

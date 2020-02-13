import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ProfileRequesterService {
  private standardUrl = "/users";
  constructor(private httpProfile:HttpClient) { }

  getProfile(badge:String){
    let headers = new HttpHeaders();
    return this.httpProfile.get("${this.standardUrl}/${badge}" + badge,{headers});
  }
}

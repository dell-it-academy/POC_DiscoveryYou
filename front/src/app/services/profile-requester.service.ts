import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ProfileRequesterService {
  private standardUrl = "http://localhost:8080/users/";
  constructor(private httpProfile:HttpClient) { }

  getProfile(badge:String){
    let headers = new HttpHeaders();
    headers.append("Accept","application/json");
    headers.append("Content-Type","application/json");

    return this.httpProfile.get(`${this.standardUrl}${badge}`,{headers});
  }
}

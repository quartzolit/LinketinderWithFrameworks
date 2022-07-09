import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpRequestService, Person } from 'src/app/shared';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  public loggedPerson:Person;

  constructor(private apiRequest:HttpRequestService, private router:Router) { }

  ngOnInit(): void {
    this.getData()
   
  }

  getData(){

    if(!this.apiRequest.loggedPerson){
      this.router.navigate([''])

    }
    this.loggedPerson = this.apiRequest.loggedPerson;
  }

  logout(){
    this.loggedPerson = null;
    this.apiRequest.loggedPerson = null;
    //create variable people = null
    this.router.navigate([''])
  }

}

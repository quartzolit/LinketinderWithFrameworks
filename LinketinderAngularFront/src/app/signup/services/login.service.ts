import { Injectable } from '@angular/core';
import { HttpRequestService, Person } from 'src/app/shared';
import { __values } from 'tslib';
import { PeopleRegex } from '../components/signup/people-regex.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private apiRequest:HttpRequestService) { }

    async login(email:string, senha:string):Promise<Person>{

      let loggedPersonString;
      
      loggedPersonString =await this.apiRequest.receiveloggedPersonIfExistFromServer(email,senha)

      if(loggedPersonString){
        console.log(loggedPersonString)
      }

      let loggedPerson:Person = JSON.parse(loggedPersonString)

      return loggedPerson
  }
}

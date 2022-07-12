import { Injectable } from '@angular/core';
import { HttpRequestService, Person } from 'src/app/shared';

@Injectable({
  providedIn: 'root'
})
export class CardsService {
  private updatedLoggedPerson: Person;

  constructor(private apiRequest:HttpRequestService) { }

  async getLogged(){

    if (!this.apiRequest.loggedPerson.approval){
      this.apiRequest.loggedPerson.approval=[]
    }
    if (!this.apiRequest.loggedPerson.disapproval){
      this.apiRequest.loggedPerson.disapproval=[]
    }
    if (!this.apiRequest.loggedPerson.vacancy){
      this.apiRequest.loggedPerson.vacancy=[]
    }

    return await this.apiRequest.loggedPerson
  }

  async getPeopleList(type:string){
    await this.apiRequest.getPeopleListBasedOnLoggedPerson(type)

    let separatingSkills: string;

    for(let person of this.apiRequest.peopleList){
      separatingSkills = JSON.stringify(person.skills)

      person.skills = separatingSkills.match(/\w+/g)

      if(!person.approval){
        person.approval = []
      }
      if(!person.disapproval){
        person.disapproval = []
      }
      if(person.type=='company' && !person.vacancy){
        person.vacancy =[]
      }
    }

    return this.apiRequest.peopleList

  }


}

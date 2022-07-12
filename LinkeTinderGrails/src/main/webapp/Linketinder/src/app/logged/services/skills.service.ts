import { Injectable } from '@angular/core';
import { HttpRequestService, Person } from 'src/app/shared';
import { CardsService } from './cards.service';

@Injectable({
  providedIn: 'root'
})
export class SkillsService {

  constructor(private apiRequest:HttpRequestService) { }



  addAndUpdate(selectedSkill:string, person: Person): Person{

    if(selectedSkill=="select-one"){
      return person

    }

    if(person.skills){

    let isOnTheList = person.skills.find(sk=>{
      sk==selectedSkill
    })

    if(isOnTheList){
      return person
    }
    person.skills.push(selectedSkill)
  }else{
    person.skills = []
    person.skills.push(selectedSkill)
  }

   
    this.update(person)

    this.apiRequest.loggedPerson = person
    

    return person
  }

  removeAndUpdate(selectedSkill:string, person: Person): Person{

    if(person.skills){
      let index = person.skills.indexOf(selectedSkill)

      person.skills.splice(index,1)

      this.update(person)

    }

    this.apiRequest.loggedPerson = person  
  
    
    return person;
  }

  update(person:Person){
    this.apiRequest.updatePersonInfo(person)
  }



}

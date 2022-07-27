import { Injectable } from '@angular/core';
import { HttpRequestService, Person, Skill } from 'src/app/shared';
import { CardsService } from './cards.service';

@Injectable({
  providedIn: 'root'
})
export class SkillsService {

  constructor(private apiRequest:HttpRequestService) { }



  addAndUpdate(selectedSkill:string, person: Person, level:string): Person{

    if(selectedSkill=="select-one"){
      return person

    }

    if(person.skills){

    let isOnTheList = person.skills.find(sk=>{
      sk.skillName==selectedSkill
    })

    if(isOnTheList){
      return person
    }

    let newSkill = new Skill()

    newSkill.skillName = selectedSkill
    newSkill.level = level

    
    person.skills.push(newSkill)

  }else{
    person.skills = []

    let newSkill = new Skill()
    newSkill.skillName = selectedSkill
    newSkill.level = level

    
    person.skills.push(newSkill)
  }

   
    this.update(person)

    this.apiRequest.loggedPerson = person
    

    return person
  }

  removeAndUpdate(selectedSkill:string, person: Person): Person{

    if(person.skills){
      person.skills.filter( (skill) =>{
        
        return skill.skillName!==selectedSkill
      })

      this.update(person)

    }

    this.apiRequest.loggedPerson = person  
  
    
    return person;
  }

  update(person:Person){
    this.apiRequest.updatePersonInfo(person)
  }



}

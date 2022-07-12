import { Injectable } from '@angular/core';
import { HttpRequestService, Person, Vacancy } from 'src/app/shared';

@Injectable({
  providedIn: 'root'
})
export class VacancyService {

  constructor(private apiRequest:HttpRequestService){}


  async addVacancy(person:Person, vacancyTitle: string, vacancySkills: string[]):Promise<Person>{

    person.vacancy.push(new Vacancy(vacancyTitle,vacancySkills))

    let updatedPerson = await this.apiRequest.addVacancy(person)
    
    return updatedPerson
  }

  async deleteVacancy(person:Person, title:string):Promise<Person>{

    let index:number;



    index = person.vacancy.findIndex((vacancy)=>{
        return vacancy.title === title ? vacancy.id : null
    })

    person = await this.apiRequest.removeVacancy(person, index);

    return person
  }


}

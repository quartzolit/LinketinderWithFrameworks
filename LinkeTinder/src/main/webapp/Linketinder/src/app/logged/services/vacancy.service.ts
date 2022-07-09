import { Injectable } from '@angular/core';
import { HttpRequestService, Person, Vacancy } from 'src/app/shared';

@Injectable({
  providedIn: 'root'
})
export class VacancyService {

  constructor(private apiRequest:HttpRequestService){}


  addVacancy(person:Person, vacancyTitle: string, vacancySkills: string[]):Person{

    person.vacancy.push(new Vacancy(vacancyTitle,vacancySkills))
    
    return person
  }

  deleteVacancy(person:Person, title:string):Person{

    let index:number;

    index = person.vacancy.findIndex((vacancy)=>{
        return vacancy.title === title
    })

    person.vacancy.splice(index,1)

    return person
  }


}

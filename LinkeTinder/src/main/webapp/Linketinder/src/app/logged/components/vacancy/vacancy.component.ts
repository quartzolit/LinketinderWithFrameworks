import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { HttpRequestService, Person, Vacancy } from 'src/app/shared';
import { VacancyService } from '../../services';

@Component({
  selector: 'app-vacancy',
  templateUrl: './vacancy.component.html',
  styleUrls: ['./vacancy.component.css']
})
export class VacancyComponent implements OnInit {

  public loggedPerson: Person;
  private router:Router;
  public addVacancy: boolean = false
  public vacancyTitle: string;
  public vacancySkills: string[];

  constructor(private vacancyService:VacancyService, private apiRequest:HttpRequestService
    , privaterouter:Router) { }

  ngOnInit(): void {
    this.getData()
  }

  getData(){

    if(!this.apiRequest.loggedPerson){
      this.router.navigate([''])

    }
    this.loggedPerson = this.apiRequest.loggedPerson;

    if(!this.loggedPerson.vacancy){
      this.loggedPerson.vacancy = []
    }

  }

  showVacancyInput(){
    this.addVacancy= !this.addVacancy;
  }

  addNewVacancy(){
    this.loggedPerson = this.vacancyService.addVacancy(this.loggedPerson, this.vacancyTitle, this.vacancySkills)
    this.addVacancy= !this.addVacancy;
  }

  deleteVacancy(title:string){
    this.loggedPerson = this.vacancyService.deleteVacancy(this.loggedPerson,title)

  }

}

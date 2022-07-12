import { Injectable } from '@angular/core';
import axios, { AxiosResponse } from "axios";
import { filter } from 'rxjs';
import { Person } from './Person.model';
import { Vacancy } from './Vacancy.model';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {
  private api = axios.create({
    baseURL: 'http://localhost:8085/ZG-LinkeTinder2'
})

  private apiGrails = axios.create({
    baseURL: 'http://localhost:8080'
  })

  public loggedPerson:Person;
  public peopleList:Person[];

  constructor() { }

  createPersonSignup(body:Person){
    let status:number;
    let check:boolean = false;

    this.api.post('/person',JSON.stringify(body)).then((res) =>{
      status = res.status
    })

    if(status==201){
      check=true;
    }

    return check;    
  }

  async receiveloggedPersonIfExistFromServer(email:string, password:string):Promise<string>{

    let sendJson ={
      userEmail: email,
      userPassword:password
    }
    
    let data = await this.api.post('/login',JSON.stringify(sendJson)).then(res=>{
      this.loggedPerson=res.data

      this.loggedPerson.skills = res.data.skills.match(/\w+/g)
      return res.data
    })


    return JSON.stringify(data)


  }

  updatePersonInfo(body:Person):void{

    this.api.post('/update/person', JSON.stringify(body));

  }

  async getPeopleListBasedOnLoggedPerson(type:string){

    let data = await this.api.get(`/person?type=${type}`).then(res=>{
      this.peopleList = res.data

    })

  }

 async addVacancy(body:Person):Promise<Person>{

  body.vacancy.forEach(async vacancy=>{
    if(!vacancy.id){
      await this.apiGrails.post(`/vacancy/${body.id}`, JSON.stringify(vacancy),{
        headers:{
          'Content-Type': 'application/json'
        }
      }).then(res=>{
        vacancy.id = res.data    
      })
    }
    
  })


  return body  
  
 }

 async removeVacancy(body:Person,id:number):Promise<Person>{

  body.vacancy.forEach(async vacancy=>{
    if(vacancy.id==id){
      await this.apiGrails.delete(`/vacancy/${vacancy.id}`,{
        headers:{
          'Content-Type': 'application/json'
        }
      }).then(res=>{
        vacancy.id = null
      })
    }  
  })

  body.vacancy = body.vacancy.filter(vacancy=>{
    return vacancy.id!=null?vacancy:null   
  })


  return body  
  
 }

}

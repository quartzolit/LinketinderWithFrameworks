import { Injectable } from '@angular/core';
import axios, { AxiosResponse } from "axios";
import { config, filter } from 'rxjs';
import { Person } from './Person.model';
import { Vacancy } from './Vacancy.model';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {
  private api = axios.create({
    baseURL: 'http://localhost:8081/api'
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

    let option = {
      headers:{
        'Accept': 'application/json',
        'Content-type':'application/json'
      }
    }

    this.api.post('/person',JSON.stringify(body),option).then((res) =>{
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

    let option = {
      headers:{
        'Accept': 'application/json',
        'Content-type':'application/json'
      }
    }
    
    let data = await this.api.post('/person/login',JSON.stringify(sendJson),option).then(res=>{
      this.loggedPerson=res.data

      if(this.loggedPerson.skills){
        this.loggedPerson.skills = res.data.skills.match(/\w+/g)
      }
      else{
        this.loggedPerson.skills=[]
      }
      
      return res.data
    })


    return JSON.stringify(data)


  }

  updatePersonInfo(body:Person):void{

    let option = {
      headers:{
        'Accept': 'application/json',
        'Content-type':'application/json'
      }
    }

    this.api.post('/update/person', JSON.stringify(body),option);

  }

  async getPeopleListBasedOnLoggedPerson(type:string){

    let option = {
      headers:{
        'Accept': 'application/json',
        'Content-type':'application/json'
      }
    }

    let data = await this.api.get(`/person?type=${type}`,option).then(res=>{
      this.peopleList = res.data

    })

  }

 async addVacancy(body:Person):Promise<Person>{

  let option = {
    headers:{
      'Accept': 'application/json',
      'Content-type':'application/json'
    }
  }

  body.vacancy.forEach(async vacancy=>{
    if(!vacancy.id){
      await this.apiGrails.post(`/vacancy/${body.id}`, JSON.stringify(vacancy),option).then(res=>{
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

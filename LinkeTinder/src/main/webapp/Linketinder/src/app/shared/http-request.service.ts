import { Injectable } from '@angular/core';
import axios, { AxiosResponse } from "axios";
import { Person } from './Person.model';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {
  private api = axios.create({
    baseURL: 'http://localhost:8085/ZG-LinkeTinder2'
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

}

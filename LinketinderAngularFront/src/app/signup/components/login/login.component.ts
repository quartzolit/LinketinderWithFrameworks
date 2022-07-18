import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from 'src/app/shared';
import { __values } from 'tslib';
import { LoginService } from '../../services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //[routerLink]="myVar ? ['/logged'] : ['']"

  public myVar:boolean = false

  public email: string;
  public password: string;

  public pessoa:Person;

  constructor(private loginService:LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  async tryToLogin():Promise<void>{

    try{
      let data= await Promise.resolve(this.loginService.login(this.email,this.password)).then(values=>{
        return values
      })
  
      this.pessoa = data;
  
      if(this.pessoa){
        this.router.navigate(["/logged"])
      }  

    }catch(err){
      console.log(err)
      
    }

    
  }

}

import { Component, OnInit } from '@angular/core';
import { Person } from 'src/app/shared';
import { SignupService } from '../../services';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public person:Person = new Person;
  public result: any;
  public birthdate:string;
  public confirmPassword:string;
  public radioCandidate: string = 'candidate';

  constructor(private signupService: SignupService) { }

  ngOnInit(): void {
  
  }

  onItemChange(value){
    this.radioCandidate = value.target.value
 }

 cadastrar():void{

  console.log(this.birthdate)

  this.person.type = this.radioCandidate
  if(this.birthdate.length>8){
    this.person.birthdate = new Date(this.birthdate)
  }
  this.result = this.signupService.cadastrar(this.person)

  if(this.result.check == true){
    window.alert('Usuário cadastrado com sucesso!')
  }
  else{
    window.alert(this.result.reason)
  }
 }
}

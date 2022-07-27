import { Injectable } from '@angular/core';
import { HttpRequestService, Person } from 'src/app/shared';
import { PeopleRegex } from '../components/signup/people-regex.model';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private api:HttpRequestService) { }

  cadastrar(body:Person): any{
    let check: boolean = false;
    let reason: string = 'Cadastro realizado com sucesso';
    let result:any={
      check : check,
      reason: reason
    }

    result = this.validateForm(body)

    if(result.check == true){
      result.check = this.api.createPersonSignup(body)

      if(result.check == false){
        reason = 'Existe um candidato com o e-mail especificado'
      }

    }




    return result
  }

  validateForm(body:Person):any{
    let validate:boolean = true;
    let reason: string = ''
    let regex:PeopleRegex = new PeopleRegex;

  
    if(!body.email.match(regex.emailRegex)){
      validate = false;
      reason = 'Email inválido'
      return {check: validate, reason: reason}
    }
    if(!body.password.match(regex.passwordRegex)){
      validate = false;
      reason = `Senha Inválida. Sua senha precisa ter entre 8 a 20 caracteres, um caracter com letra
       maiuscula, 1 caractere especial e ao menos 1 número`
       return {check: validate, reason: reason}
    }
    if(body.type == 'candidate'){
      if(!body.cpf.match(regex.cpfRegex)){
        validate = false;
        reason = 'CPF inválido (xxx.xxx.xxx-xx)'
        return {check: validate, reason: reason}
      }

      if(!body.name.match(regex.nameRegex)){
        validate = false;
        reason = 'Nome Precisa ser maior do que 3 caracteres'
        return {check: validate, reason: reason}
      }
    }
    if(body.type == 'company'){
      if(!body.cnpj.match(regex.cnpjRegex)){
        validate = false;
        reason = 'cnpj inválido (xx.xxx.xxx/0001-xx ou xx.xxx.xxx/0002-xx)'
        return {check: validate, reason: reason}
      }
    }
    if(!body.cep.match(regex.cepRegex)){
      validate = false;
      reason = 'cep inválido (xx.xxx-xxx ou xxxxx-xxx)'
      return {check: validate, reason: reason}
    }


    return {check: validate, reason: reason}
  }
}

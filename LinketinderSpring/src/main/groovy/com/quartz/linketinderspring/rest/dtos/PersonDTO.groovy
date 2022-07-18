package com.quartz.linketinderspring.rest.dtos

import com.quartz.linketinderspring.domain.entity.Person
import com.quartz.linketinderspring.domain.entity.Vacancy

import java.time.LocalDate

/**

 {
     "type": string;
     "id":number;
     "email": string;
     "password": string;
     "name": string;
     "surName": string;
     "dob": Date;
     "cpf": string;
     "companyName": string;
     "cnpj": string;
     "country": string;
     "cep": string;
     "state": string;
     "description": string;
     "skills": string[]; ->será implementado em outro framework
     "vacancy": Vacancy[]; ->será implementado em outro framework
     "approval": Person[]; ->será implementado em outro framework
     "disapproval": Person[]; ->será implementado em outro framework

 }

 * */

class PersonDTO {
    String type
    Integer id
    String email
    String password
    String name
    String surName
    LocalDate birthdate
    String cpf
    String companyName
    String cnpj
    String country
    String cep
    String state
    String description
    List<String> skills
    List<Vacancy> vacancy
    List<PersonDTO> approval
    List<PersonDTO> disapproval
}

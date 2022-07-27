package com.quartz.controllers.dto

import com.quartz.domain.entity.Vacancy
import groovy.transform.ToString

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

@ToString
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
    List<SkillDTO> skills
    List<VacancyDTO> vacancy
    List<PersonDTO> approval
    List<PersonDTO> disapproval
}

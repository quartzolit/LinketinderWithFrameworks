package com.quartz.linketinderspring.services

import com.quartz.linketinderspring.domain.entity.Candidate
import com.quartz.linketinderspring.domain.entity.Company
import com.quartz.linketinderspring.domain.entity.Person
import com.quartz.linketinderspring.rest.dtos.LoginDTO
import com.quartz.linketinderspring.rest.dtos.PersonDTO

interface IPersonService {

    Person save(PersonDTO dto);

    Optional<PersonDTO> getPersonByEmailAndPassword(LoginDTO dto)

    Candidate convertToCandidate(PersonDTO dto)

    Company convertToCompany(PersonDTO dto)

}
package com.quartz.linketinderspring.rest.controllers

import com.quartz.linketinderspring.domain.entity.Candidate
import com.quartz.linketinderspring.domain.entity.Company
import com.quartz.linketinderspring.domain.entity.Person
import com.quartz.linketinderspring.rest.dtos.LoginDTO
import com.quartz.linketinderspring.rest.dtos.PersonDTO
import com.quartz.linketinderspring.services.IPersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/person")
class PersonController {

    IPersonService service

    PersonController(IPersonService service){
        this.service = service
    }

    @PostMapping
    @ResponseStatus(CREATED)
    void saveNewUser(@RequestBody PersonDTO dto){
        Person user = service.save(dto)

        if(!user){
            throw new ResponseStatusException(NOT_FOUND,"Email already used")
        }
    }

    @PostMapping("/login")
    @ResponseStatus(OK)
    Optional<PersonDTO> receiveLogin(@RequestBody LoginDTO dto){

        Optional<PersonDTO> userDto = service.getPersonByEmailAndPassword(dto)

        return userDto
    }
}

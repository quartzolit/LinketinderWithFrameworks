package com.quartz.linketinderspring.rest.controllers

import com.quartz.linketinderspring.domain.entity.Candidate
import com.quartz.linketinderspring.domain.entity.Company
import com.quartz.linketinderspring.domain.entity.Person
import com.quartz.linketinderspring.rest.dtos.LoginDTO
import com.quartz.linketinderspring.rest.dtos.PersonDTO
import com.quartz.linketinderspring.services.IPersonService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/api/person")
class PersonController {

    IPersonService service

    PersonController(IPersonService service){
        this.service = service
    }

    @PostMapping("/signup")
    @ResponseStatus(CREATED)
    @CrossOrigin(origins = "http://localhost:4200")
    //@ApiOperation(value = "addNewUser")
    void saveNewUser(@RequestBody @Valid PersonDTO dto){
        Person user = service.save(dto)

        if(!user){
            throw new ResponseStatusException(NOT_FOUND,"Email already used")
        }
    }

    @PostMapping("/login")
    @ResponseStatus(OK)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    Optional<PersonDTO> receiveLogin(@RequestBody @Valid LoginDTO dto){
        Optional<PersonDTO> userDto = service.getPersonByEmailAndPassword(dto)

        return userDto
    }

    @GetMapping
    @ResponseStatus(OK)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    List<PersonDTO> getAllData(@RequestParam String type){

        List<PersonDTO> list = service.getDataByType(type)

        return list
    }
}

package com.quartz.linketinderspring

import com.quartz.linketinderspring.domain.entity.Candidate
import com.quartz.linketinderspring.exceptions.EmailAlreadyExistException
import com.quartz.linketinderspring.rest.controllers.PersonController
import com.quartz.linketinderspring.rest.dtos.LoginDTO
import com.quartz.linketinderspring.rest.dtos.PersonDTO
import com.quartz.linketinderspring.services.implementation.PersonService
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@WebMvcTest(controllers =PersonController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE))
class PersonControllerTest extends Specification{

    @Autowired
    MockMvc mvcController


    @MockBean
    PersonService service

    PersonDTO person = new PersonDTO(email: "teste11@teste.net.br",
            type: "candidate", name: "teste", surName: "show",
            cpf: "12312312315", birthdate: LocalDate.now(),
            state: "Sao Paulo", country: "Brasil",
            description: "nice", cep: "11222-000",
            password: "123456"
    )


    def"/api/person/signup returns a Candidate"(){
        given:

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        PersonDTO person = new PersonDTO(email: "teste11@teste.net.br",
                type: "candidate", name: "teste", surName: "show",
                cpf: "12312312315", birthdate: LocalDate.now(),
                state: "Sao Paulo", country: "Brasil",
                description: "nice", cep: "11222-000",
                password: "123456"
        )

        Candidate candidate = new Candidate(
                name: person.name, surName: person.surName,
                email: person.email, cpf: person.cpf,
                birthdate: person.birthdate, state: person.state,
                country: person.country, description: person.description,
                cep: person.cep, password: person.password
        )

        def json = JsonOutput.toJson(person)


//        candidates.save(person) >> candidate
//        candidates.findByEmail(personCandidate.email)>> new Optional<Person>()
//        companies.findByEmail(personCandidate.email)>> new Optional<Person>()

        when:
            mvcController.perform(MockMvcRequestBuilders.post("/api/person/signup")
                .content(json).contentType(MediaType.APPLICATION_JSON).accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isCreated())

        then:
            notThrown(EmailAlreadyExistException)

    }

    def"/api/person/login returns a PersonDTO"(){
        given:

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        LoginDTO person = new LoginDTO(userEmail: "teste3@testenet.br", userPassword: "123123")

        def json = JsonOutput.toJson(person)


        when:

        ResultActions status = mvcController.perform(MockMvcRequestBuilders.post("/api/person/login")
                .content(json).contentType(MediaType.APPLICATION_JSON).accept("application/json"))


        then:

        status.andExpect(MockMvcResultMatchers.status().isOk())



    }

}

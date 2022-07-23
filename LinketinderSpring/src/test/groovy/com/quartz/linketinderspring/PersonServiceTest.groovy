package com.quartz.linketinderspring

import com.quartz.linketinderspring.domain.entity.Candidate
import com.quartz.linketinderspring.domain.entity.Company
import com.quartz.linketinderspring.domain.entity.Person
import com.quartz.linketinderspring.domain.repository.Candidates
import com.quartz.linketinderspring.domain.repository.Companies
import com.quartz.linketinderspring.exceptions.EmailAlreadyExistException
import com.quartz.linketinderspring.exceptions.InvalidEmailOrPasswordException
import com.quartz.linketinderspring.rest.dtos.LoginDTO
import com.quartz.linketinderspring.rest.dtos.PersonDTO
import com.quartz.linketinderspring.services.implementation.PersonService
import spock.lang.Specification

import java.time.LocalDate

class PersonServiceTest extends Specification{


    Candidates candidateRepository = Mock(Candidates)
    Companies companyRepository = Mock(Companies)

    PersonDTO personCandidate = new PersonDTO(email: "teste10@teste.net.br",
            type: "candidate", name: "teste", surName: "show",
            cpf: "12312312315", birthdate: LocalDate.now(),
            state: "Sao Paulo", country: "Brasil",
            description: "nice", cep: "11222-000",
            password: "123456"
    )

    PersonDTO personCompany = new PersonDTO(email: "teste10@teste.net.br",
            type: "company", companyName: "Atacado do teste",
            cnpj: "11.123.123/0001-12", state: "Sao Paulo", country: "Brasil",
            description: "nice", cep: "11222-000",
            password: "123456"
    )

    def "Returns a Candidate given a PersonDTO"(){

        given:

        Candidate candidate = new Candidate(
                name: personCandidate.name, surName: personCandidate.surName,
                email: personCandidate.email, cpf: personCandidate.cpf,
                birthdate: personCandidate.birthdate, state: personCandidate.state,
                country: personCandidate.country, description: personCandidate.description,
                cep: personCandidate.cep, password: personCandidate.password
        )

        and:
        candidateRepository.save(personCandidate) >> candidate
        candidateRepository.findByEmail(personCandidate.email)>> new Optional<Person>()
        companyRepository.findByEmail(personCandidate.email)>> new Optional<Person>()
        PersonService personService = new PersonService(candidateRepository, companyRepository)
        when:
        Candidate expectedCandidate = personService.save(personCandidate)

        then:
        expectedCandidate.email == candidate.email

    }

    def "Returns a Company given a PersonDTO"(){

        given:

        Company company = new Company(
                name: personCompany.companyName,
                cnpj: personCompany.cnpj,
                email: personCompany.email,
                state: personCompany.state,
                country: personCompany.country, description: personCompany.description,
                cep: personCompany.cep, password: personCompany.password
        )

        and:
        candidateRepository.save(personCompany) >> company
        candidateRepository.findByEmail(personCompany.email)>> new Optional<Person>()
        companyRepository.findByEmail(personCompany.email)>> new Optional<Person>()
        PersonService personService = new PersonService(candidateRepository, companyRepository)
        when:
        Company expectedCompany = personService.save(personCompany)

        then:
        expectedCompany.email == company.email

    }

    def "Returns Exception given a PersonDTO with used e-mail"(){

        given:

        Company company = new Company(
                name: personCompany.companyName,
                cnpj: personCompany.cnpj,
                email: personCompany.email,
                state: personCompany.state,
                country: personCompany.country, description: personCompany.description,
                cep: personCompany.cep, password: personCompany.password
        )

        and:
        candidateRepository.save(personCompany) >> company
        candidateRepository.findByEmail(personCompany.email)>> new Optional<Company>(company)
        companyRepository.findByEmail(personCompany.email)>> new Optional<Person>()
        PersonService personService = new PersonService(candidateRepository, companyRepository)
        when:
        Company expectedCompany = personService.save(personCompany)

        then:
        thrown(EmailAlreadyExistException)

    }

    def "receive Candidate through PersonDTO given LoginDTO"(){
        given:
        Candidate candidate = new Candidate(
                name: personCandidate.name, surName: personCandidate.surName,
                email: personCandidate.email, cpf: personCandidate.cpf,
                birthdate: personCandidate.birthdate, state: personCandidate.state,
                country: personCandidate.country, description: personCandidate.description,
                cep: personCandidate.cep, password: personCandidate.password
        )

        LoginDTO login= new LoginDTO(
            userEmail: "teste10@teste.net.br" ,userPassword:"123123"
        )
        and:
        candidateRepository.findByEmailAndPassword(login.userEmail, login.userPassword) >> new Optional<Candidate>(candidate)
        companyRepository.findByEmailAndPassword(login.userEmail,login.userPassword) >> new Optional<Company>()

        PersonService service = new PersonService(candidateRepository, companyRepository)

        when:
        Optional<PersonDTO> candidateExpected= service.getPersonByEmailAndPassword(login)

        then:

        !candidateExpected.isEmpty()




    }

    def "receive Company through PersonDTO given LoginDTO"(){
        given:
        Company company = new Company(
                name: personCompany.companyName,
                cnpj: personCompany.cnpj,
                email: personCompany.email,
                state: personCompany.state,
                country: personCompany.country, description: personCompany.description,
                cep: personCompany.cep, password: personCompany.password
        )

        LoginDTO login= new LoginDTO(
                userEmail: "teste10@teste.net.br" ,userPassword:"123123"
        )
        and:
        candidateRepository.findByEmailAndPassword(login.userEmail, login.userPassword) >> new Optional<Candidate>()
        companyRepository.findByEmailAndPassword(login.userEmail, login.userPassword) >> new Optional<Company>(company)

        PersonService service = new PersonService(candidateRepository, companyRepository)

        when:
        Optional<PersonDTO> companyExpected= service.getPersonByEmailAndPassword(login)

        then:

        !companyExpected.isEmpty()




    }

    def "receive No account found Exception given LoginDTO"(){
        given:

        LoginDTO login= new LoginDTO(
                userEmail: "teste10@teste.net.br" ,userPassword:"123123"
        )
        and:
        candidateRepository.findByEmailAndPassword(login.userEmail, login.userPassword) >> new Optional<Candidate>()
        companyRepository.findByEmailAndPassword(login.userEmail, login.userPassword) >> new Optional<Company>()

        PersonService service = new PersonService(candidateRepository, companyRepository)

        when:
        Optional<PersonDTO> personExpected= service.getPersonByEmailAndPassword(login)

        then:

        thrown(InvalidEmailOrPasswordException)

    }

}

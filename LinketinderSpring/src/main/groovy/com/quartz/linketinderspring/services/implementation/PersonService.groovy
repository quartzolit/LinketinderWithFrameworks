package com.quartz.linketinderspring.services.implementation

import com.quartz.linketinderspring.domain.entity.Candidate
import com.quartz.linketinderspring.domain.entity.Company
import com.quartz.linketinderspring.domain.entity.Person
import com.quartz.linketinderspring.domain.repository.Candidates
import com.quartz.linketinderspring.domain.repository.Companies
import com.quartz.linketinderspring.exceptions.EmailAlreadyExistException
import com.quartz.linketinderspring.exceptions.InvalidEmailOrPasswordException
import com.quartz.linketinderspring.rest.dtos.LoginDTO
import com.quartz.linketinderspring.rest.dtos.PersonDTO
import com.quartz.linketinderspring.rest.dtos.SkillDTO
import com.quartz.linketinderspring.services.IPersonService
import org.springframework.stereotype.Service

@Service
class PersonService implements IPersonService{

    Candidates candidatesRepository;

    Companies companiesRepository;

    PersonService(Candidates candidates, Companies companies){
        this.candidatesRepository = candidates
        this.companiesRepository = companies
    }



    @Override
    Person save(PersonDTO dto) {

        String type = dto.type

        Optional<Person> person = candidatesRepository.findByEmail(dto.email)

        if(person.isEmpty()){
            person = companiesRepository.findByEmail(dto.email)
        }


        if(person.isEmpty() && type.equals("candidate")){
           Candidate candidate = convertToCandidate(dto)
            candidatesRepository.save(candidate)
            return candidate
        }
        else if(person.isEmpty() && type.equals("company")) {
            Company company = convertToCompany(dto)
            companiesRepository.save(company)
            return company
        }
        else {

            throw new EmailAlreadyExistException()

        }



        return null
    }


    @Override
    Optional<PersonDTO> getPersonByEmailAndPassword(LoginDTO dto) {

        Optional<Candidate> candidate = candidatesRepository.findByEmailAndPassword(dto.userEmail, dto.userPassword)
                .map(ca->{
                    return new PersonDTO(
                            id: ca.id, type: 'candidate',
                            name: ca.name, surName: ca.surName,
                            email: ca.email, birthdate: ca.birthdate,
                            state: ca.state, cep: ca.cep,
                            cpf: ca.cpf, country: ca.country,
                            description: ca.description,
                            password: ca.password
                    )

                })

        if (candidate.isEmpty()){
            Optional<Company> company = companiesRepository.findByEmailAndPassword(dto.userEmail, dto.userPassword)
                    .map(co->{
                        return new PersonDTO(
                                id: co.id, type: 'company',
                                companyName: co.name, cnpj: co.cnpj,
                                email: co.email, state: co.state,
                                cep: co.cep,country: co.country,
                                description: co.description,
                                password: co.password)
                    })

            if(company.isPresent()){
                return company
            }
        }
        else {
            return candidate
        }


        throw new InvalidEmailOrPasswordException()

        return null
    }


    Candidate convertToCandidate(PersonDTO dto){
        return new Candidate(name: dto.name,surName: dto.surName,
                email: dto.email,password: dto.password,
                birthdate: dto.birthdate,state: dto.state,
                cep: dto.cep, country: dto.country,description: dto.description)

    }

    Company convertToCompany(PersonDTO dto){
        return new Company(name: dto.companyName,email: dto.email,
                password: dto.password,cnpj: dto.cnpj,
                cep: dto.cep,state: dto.state,
                country: dto.country, description: dto.description)

    }

    @Override
    List<PersonDTO> getDataByType(String type) {
        List<PersonDTO> list = new ArrayList<>()
        List<Company> companies = new ArrayList<>()
        List<Candidate> candidates = new ArrayList<>()


        if(type == 'candidate'){

            companies = companiesRepository.findAll()


            list = companies.forEach(person->{


                List<SkillDTO> skillDTO = new ArrayList<>()


                return new PersonDTO(
                        type: 'company',
                        id: person.id,
                        email: person.email,
                        companyName: person.name,
                        cnpj: person.cnpj,
                        state: person.state,
                        cep: person.cep,
                        country: person.country,
                        vacancy: person.vacancies,
                        skills: skillDTO)
            })

            return list
        }
        else {
            candidates = candidatesRepository.findAll()

            list = candidates.forEach(person->{
                List<SkillDTO> skillDTO = new ArrayList<>()
                return  new PersonDTO(
                        type: 'candidate',
                        id: person.id,
                        email: person.email,
                        name: person.name,
                        surName: person.surName,
                        state: person.state,
                        cep: person.cep,
                        country: person.country,
                        birthdate: person.birthdate,
                        cpf: person.cpf,
                        skills: SkillDTO
                )
            })

            return list
        }

        return list
    }

}

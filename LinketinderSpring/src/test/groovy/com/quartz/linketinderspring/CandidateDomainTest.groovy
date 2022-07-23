package com.quartz.linketinderspring

import com.quartz.linketinderspring.domain.entity.Candidate
import com.quartz.linketinderspring.domain.repository.Candidates
import org.assertj.core.error.ShouldNotBeNull
import spock.lang.Specification

import java.time.LocalDate

class CandidateDomainTest extends Specification{

    def candidateMockRepository = Mock(Candidates.class)

    Candidate candidate = new Candidate(
            email: "teste@teste.net.br",
            name: "teste", surName: "show",
            cpf: "12312312315", birthdate: LocalDate.now(),
            state: "São Paulo", country: "Brasil",
            description: "nice", cep: "11222-000",
            password: "123456")

    def "Candidate cannot be inserted without email"(){

        given: "New Candidate"

        Candidate expectedCandidate = new Candidate()


        Candidate candidate2 = new Candidate(
                name: "teste", surName: "show",hg
                cpf: "12312312315", birthdate: LocalDate.now(),
                state: "São Paulo", country: "Brasil",
                description: "nice", cep: "11222-000",
                password: "123456")

        and:
            candidateMockRepository.save(candidate) >> candidate
            candidateMockRepository.save(candidate2) >> null

        when:

        expectedCandidate = candidateMockRepository.save(candidate2)

        then:

        expectedCandidate == null

    }

}

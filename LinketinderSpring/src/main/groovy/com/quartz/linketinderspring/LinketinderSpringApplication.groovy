package com.quartz.linketinderspring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class LinketinderSpringApplication {

    /*
    @Bean
    CommandLineRunner commandLineRunner(@Autowired Candidates candidates){
        return (args)->{
            Candidate c = new Candidate(email: "teste@teste.net.br", name: "Testrix",
                    surName: "NetTest", birthdate: LocalDate.now(),
                    cpf: "111.222.333-11", description: "cooler so much",
                    state: "Sao Paulo",country: "Brazil", cep: "11000-120",password: "123123")

            candidates.save(c)

            println(candidates.findById(c.id))
        }
    }

     */

    static void main(String[] args) {
        SpringApplication.run(LinketinderSpringApplication, args)
    }

}

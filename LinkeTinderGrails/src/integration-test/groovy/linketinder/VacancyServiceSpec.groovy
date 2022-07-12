package linketinder

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class VacancyServiceSpec extends Specification {

    VacancyService vacancyService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Vacancy(...).save(flush: true, failOnError: true)
        //new Vacancy(...).save(flush: true, failOnError: true)
        //Vacancy vacancy = new Vacancy(...).save(flush: true, failOnError: true)
        //new Vacancy(...).save(flush: true, failOnError: true)
        //new Vacancy(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //vacancy.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        vacancyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Vacancy> vacancyList = vacancyService.list(max: 2, offset: 2)

        then:
        vacancyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        vacancyService.count() == 5
    }

    void "test delete"() {
        Long vacancyId = setupData()

        expect:
        vacancyService.count() == 5

        when:
        vacancyService.delete(vacancyId)
        datastore.currentSession.flush()

        then:
        vacancyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Vacancy vacancy = new Vacancy()
        vacancyService.save(vacancy)

        then:
        vacancy.id != null
    }
}

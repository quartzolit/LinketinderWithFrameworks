package linketinder

import grails.rest.RestfulController
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class VacancyController extends RestfulController<Vacancy> {

    VacancyService vacancyService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    VacancyController(){
        super(Vacancy)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond vacancyService.list(params), model:[vacancyCount: vacancyService.count()]
    }

    def show(Long id) {
        respond vacancyService.get(id)
    }

    @Transactional
    def save(Vacancy vacancy) {
        if (vacancy == null) {
            render status: NOT_FOUND
            return
        }
        if (vacancy.hasErrors()) {
            respond vacancy.errors
            return
        }

        try {
            vacancyService.save(vacancy)
        } catch (ValidationException e) {
            respond vacancy.errors
            return
        }

        respond vacancy, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Vacancy vacancy) {
        if (vacancy == null) {
            render status: NOT_FOUND
            return
        }
        if (vacancy.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vacancy.errors
            return
        }

        try {
            vacancyService.save(vacancy)
        } catch (ValidationException e) {
            respond vacancy.errors
            return
        }

        respond vacancy, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || vacancyService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}

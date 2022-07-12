package linketinder

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class SkillController {

    SkillService skillService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond skillService.list(params), model:[skillCount: skillService.count()]
    }

    def show(Long id) {
        respond skillService.get(id)
    }

    @Transactional
    def save(Skill skill) {
        if (skill == null) {
            render status: NOT_FOUND
            return
        }
        if (skill.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond skill.errors
            return
        }

        try {
            skillService.save(skill)
        } catch (ValidationException e) {
            respond skill.errors
            return
        }

        respond skill, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Skill skill) {
        if (skill == null) {
            render status: NOT_FOUND
            return
        }
        if (skill.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond skill.errors
            return
        }

        try {
            skillService.save(skill)
        } catch (ValidationException e) {
            respond skill.errors
            return
        }

        respond skill, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || skillService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}

package linketinder

import grails.gorm.services.Service

@Service(Vacancy)
interface VacancyService {

    Vacancy get(Serializable id)

    List<Vacancy> list(Map args)

    Long count()

    Vacancy delete(Serializable id)

    Vacancy save(Vacancy vacancy)

}

package linketinder

import grails.gorm.services.Service

@Service(Skill)
interface SkillService {

    Skill get(Serializable id)

    List<Skill> list(Map args)

    Long count()

    Skill delete(Serializable id)

    Skill save(Skill skill)

}

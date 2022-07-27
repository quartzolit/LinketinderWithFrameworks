package com.quartz.controllers

import com.quartz.controllers.dto.PersonDTO
import com.quartz.domain.repositories.CandidatesSkills
import com.quartz.domain.repositories.CompaniesSkills
import com.quartz.domain.repositories.VacanciesSkills
import com.quartz.services.SkillControllerService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Put

@Controller(value = "/api/skill", produces = "application/json", consumes = "application/json")
class SkillController {

    SkillControllerService skillService

    SkillController(SkillControllerService skillService){

        this.skillService = skillService
    }

    @Put
    void updateSkill(@Body PersonDTO dto){

        String type = dto.type

        boolean isRelated = false

        if(type == 'candidate'){
            isRelated= skillService.addCandidateRelation(dto)
        }else {
            isRelated= skillService.addCompanyRelation(dto)
        }

        if(isRelated == false){
            throw new Exception("Unable to update skill")
        }





    }


}

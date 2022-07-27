package com.quartz.services

import com.quartz.controllers.dto.PersonDTO
import com.quartz.controllers.dto.SkillDTO
import com.quartz.controllers.dto.VacancyDTO
import com.quartz.domain.entity.Candidate
import com.quartz.domain.entity.CandidateSkill
import com.quartz.domain.entity.Company
import com.quartz.domain.entity.CompanySkill
import com.quartz.domain.entity.Skill
import com.quartz.domain.repositories.Candidates
import com.quartz.domain.repositories.CandidatesSkills
import com.quartz.domain.repositories.Companies
import com.quartz.domain.repositories.CompaniesSkills
import com.quartz.domain.repositories.Skills
import com.quartz.domain.repositories.Vacancies
import com.quartz.domain.repositories.VacanciesSkills
import jakarta.inject.Singleton

@Singleton
class SkillControllerService {

    CandidatesSkills candidatesSkills
    CompaniesSkills companiesSkills
    VacanciesSkills vacanciesSkills
    Skills skills
    Candidates candidates
    Companies companies
    Vacancies vacancies


    SkillControllerService(CandidatesSkills candidatesSkills,
                           CompaniesSkills companiesSkills,
                           VacanciesSkills vacanciesSkills,
                           Skills skills,
                           Candidates candidates,
                           Companies companies,
                           Vacancies vacancies){

        this.candidatesSkills = candidatesSkills
        this.companiesSkills = companiesSkills
        this.vacanciesSkills = vacanciesSkills
        this.skills = skills
        this.candidates = candidates
        this.companies = companies
        this.vacancies = vacancies
    }

    boolean addCandidateRelation(PersonDTO dto){

        Optional<Candidate> checkCandidate = candidates.findById(dto.id)

        if(checkCandidate.isEmpty()){
           return false
        }

        List<Skill> skillList = getListFromDTO(dto.skills)

        List<Skill> skillsToRelate = saveSkillsNotOnDB(skillList)

        List<CandidateSkill> candidateSkillsRelation = new ArrayList<>()

        skillsToRelate.forEach(skill ->{
            CandidateSkill cs = new CandidateSkill()
            cs.candidate = checkCandidate.get()
            cs.skill = skill
            SkillDTO skillDTO =dto.skills.find(it->{
                return (it.skillName == skill.skillName)? it.level.toString():null
            })
            cs.level = skillDTO.level.toString()
            candidateSkillsRelation << cs
        })

        candidatesSkills.deleteByCandidate(checkCandidate.get())
        candidatesSkills.updateAll(candidateSkillsRelation)
        return true
    }

    boolean addCompanyRelation(PersonDTO dto){

        Optional<Company> checkCompany = companies.findByEmail(dto.email)

        if(checkCompany.isEmpty()){
            return false
        }

        List<Skill> skillList = getListFromDTO(dto.skills)

        List<Skill> skillsToRelate = saveSkillsNotOnDB(skillList)

        List<CompanySkill> companySkillsRelation = new ArrayList<>()

        skillsToRelate.forEach(skill ->{
            CompanySkill cs = new CompanySkill()
            cs.company = checkCompany.get()
            cs.skill = skill
            SkillDTO skillDTO =dto.skills.find(it->{
                return (it.skillName == skill.skillName)? it.level.toString():null
            })
            cs.level = skillDTO.level.toString()
            companySkillsRelation << cs
        })

        companiesSkills.deleteByCompany(checkCompany)

        companiesSkills.updateAll(companySkillsRelation)
        return true

    }

    List<Skill> getListFromDTO(List<SkillDTO> skills){
        List<Skill> skillList = new ArrayList<>()

        skillList = skills.collect(skill->{
            Skill skillToAdd = new Skill()
            skillToAdd.skillName = skill.skillName
            return skillToAdd
        })

        return skillList
    }

    List<Skill> saveSkillsNotOnDB(List<Skill> skillList){

        List<Skill> skillsOnDB = skills.findAll()

        List<Skill> skillsToRelate = new ArrayList<>()

        for(Skill skill : skillList){

            Boolean isOnDB = skillsOnDB.find(it->{
                return (it.skillName == skill.skillName)
            })
            if(!isOnDB){
                skill = skills.save(skill)
                skillsToRelate.add(skill)
            }
            else {
                skillsToRelate.add(skillsOnDB.find(it->{
                    return (it.skillName == skill.skillName)? it : null
                }))
            }
        }

        return skillsToRelate
    }


}

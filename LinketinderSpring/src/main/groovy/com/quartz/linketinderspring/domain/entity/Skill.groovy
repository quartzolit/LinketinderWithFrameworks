package com.quartz.linketinderspring.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.persistence.criteria.CriteriaBuilder

@Entity
@Table(name = "skills")
class Skill {

    @Id
    Integer id

    @Column(name = "skill_name")
    String skillName

    @ManyToMany
    Set<Candidate> candidates

    @ManyToMany
    Set<Company> companies

    @ManyToMany
    Set<Vacancy> vacancies
}

package com.quartz.linketinderspring.domain.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "vacancies")
class Vacancy {

    @Id
    Integer id

    String title

    @ManyToOne
    @JoinColumn(name = "id_company")
    Company company

    @ManyToMany
    @JoinTable(name = "vacancies_skills",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    Set<Skill>skills


}

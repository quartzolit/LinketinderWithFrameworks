package com.quartz.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Cascade

import javax.persistence.*

@Entity
@Table(name = "vacancies")
class Vacancy {

    @Id
    Integer id

    String title

    @ManyToOne
    @JoinColumn(name = "id_company")
    Company company

    @JsonIgnore
    @OneToMany(mappedBy = "vacancy", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    Set<VacancySkill> vacancySkill


}

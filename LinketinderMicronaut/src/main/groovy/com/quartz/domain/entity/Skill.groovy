package com.quartz.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Cascade

import javax.persistence.*

@Entity
@Table(name = "skills")
class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Column(name = "skill_name", unique = true)
    String skillName

    @JsonIgnore
    @OneToMany(mappedBy = "skill", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    Set<CandidateSkill> candidateSkill

    @JsonIgnore
    @OneToMany(mappedBy = "skill", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    Set<CompanySkill> companySkill

    @JsonIgnore
    @OneToMany(mappedBy = "skill", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    Set<VacancySkill> vacancySkill
}

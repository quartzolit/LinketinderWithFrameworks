package com.quartz.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.InheritConstructors
import groovy.transform.ToString
import org.hibernate.annotations.Cascade

import javax.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "candidates")
@InheritConstructors
class Candidate extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    String name

    @Column(name="surname")
    String surName

    LocalDate birthdate

    String cpf

    @Column(name="personal_description")
    String description

    @JsonIgnore
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    Set<CandidateSkill> candidatesSkills

}

package com.quartz.linketinderspring.domain.entity

import groovy.transform.InheritConstructors
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "candidates")
@ToString
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

    @ManyToMany
    @JoinTable(
            name= "candidates_skills",
            joinColumns = @JoinColumn(name="id_candidate"),
            inverseJoinColumns = @JoinColumn(name="id_skill")
    )
    Set<Skill>skills

}

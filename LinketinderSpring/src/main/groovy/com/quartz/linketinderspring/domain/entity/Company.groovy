package com.quartz.linketinderspring.domain.entity

import groovy.transform.InheritConstructors
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.LocalDate

@Entity
@Table(name="companies")
@ToString
@InheritConstructors
class Company extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    String name

    String cnpj

    @Column(name= "company_description")
    String description

}

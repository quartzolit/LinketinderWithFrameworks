package com.quartz.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.InheritConstructors
import groovy.transform.ToString
import org.hibernate.annotations.Cascade

import javax.persistence.*

@Entity
@Table(name="companies")
@InheritConstructors
class Company extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    String name

    String cnpj

    @Column(name= "company_description")
    String description

    @JsonIgnore
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    List<Vacancy> vacancies

    @JsonIgnore
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    Set<CompanySkill> companySkill

}

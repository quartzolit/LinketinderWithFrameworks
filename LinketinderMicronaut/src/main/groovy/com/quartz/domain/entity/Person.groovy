package com.quartz.domain.entity

import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@MappedSuperclass
class Person {

    @NotEmpty(message= "email field required")
    @Column(name = "email", unique = true)
    String email

    @NotEmpty(message= "password field required")
    String password

    @NotEmpty(message= "state field required")
    String state

    @NotEmpty(message= "country field required")
    String country

    @NotEmpty(message= "cep field required")
    String cep


}


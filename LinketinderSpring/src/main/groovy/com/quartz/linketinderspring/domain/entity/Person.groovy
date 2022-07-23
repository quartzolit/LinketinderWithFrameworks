package com.quartz.linketinderspring.domain.entity

import groovy.transform.ToString
import org.aspectj.weaver.ast.Not

import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@MappedSuperclass
@ToString
class Person {

    @NotEmpty(message= "email field required")
    @NotNull
    String email

    @NotEmpty(message= "password field required")
    @NotNull
    String password

    @NotEmpty(message= "state field required")
    @NotNull
    String state

    @NotEmpty(message= "country field required")
    @NotNull
    String country

    @NotEmpty(message= "cep field required")
    @NotNull
    String cep


}


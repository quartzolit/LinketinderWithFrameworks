package com.quartz.linketinderspring.domain.entity

import groovy.transform.ToString

import javax.persistence.MappedSuperclass

@MappedSuperclass
@ToString
class Person {

    String email
    String password
    String state
    String country
    String cep


}


package com.quartz.linketinderspring.exceptions

class EmailAlreadyExist extends RuntimeException{

    EmailAlreadyExist(){
        super("E-mail already used")
    }
}

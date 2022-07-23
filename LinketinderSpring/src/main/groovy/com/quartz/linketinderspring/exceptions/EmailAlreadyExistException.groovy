package com.quartz.linketinderspring.exceptions

class EmailAlreadyExistException extends RuntimeException{

    EmailAlreadyExistException(){
        super("E-mail already used")
    }
}

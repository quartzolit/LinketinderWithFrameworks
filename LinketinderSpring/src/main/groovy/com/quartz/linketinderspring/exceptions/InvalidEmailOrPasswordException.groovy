package com.quartz.linketinderspring.exceptions

class InvalidEmailOrPasswordException extends RuntimeException{

    InvalidEmailOrPasswordException(){
        super("Invalid E-mail or Password")
    }
}

package com.quartz.linketinderspring.domain.repository


import com.quartz.linketinderspring.domain.entity.Company
import org.springframework.data.jpa.repository.JpaRepository

interface Companies extends JpaRepository<Company,Integer>{

    Optional<Company> findByEmail(String email)

    Optional<Company> findByEmailAndPassword(String email, String password)

}
package com.quartz.domain.repositories

import com.quartz.domain.entity.Candidate
import com.quartz.domain.entity.Company
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface Companies extends JpaRepository<Company,Integer> {

    Optional<Company> findByEmail(String email)
}
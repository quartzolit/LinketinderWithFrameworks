package com.quartz.domain.repositories

import com.quartz.domain.entity.Candidate
import com.quartz.domain.entity.Company
import com.quartz.domain.entity.Vacancy
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface Vacancies extends JpaRepository<Vacancy,Integer> {

    Optional<Vacancy> findByTitleAndCompany(String title, Company company)
}

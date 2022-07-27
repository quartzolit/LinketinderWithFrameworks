package com.quartz.domain.repositories

import com.quartz.domain.entity.Candidate
import com.quartz.domain.entity.Company
import com.quartz.domain.entity.CompanySkill
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CompaniesSkills extends JpaRepository<CompanySkill,Integer> {

    void deleteByCompany(Company company)

}
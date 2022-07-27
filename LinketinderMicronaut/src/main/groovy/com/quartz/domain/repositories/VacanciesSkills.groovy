package com.quartz.domain.repositories

import com.quartz.domain.entity.VacancySkill
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface VacanciesSkills extends JpaRepository<VacancySkill,Integer> {

}
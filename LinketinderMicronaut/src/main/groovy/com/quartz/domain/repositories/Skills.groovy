package com.quartz.domain.repositories

import com.quartz.domain.entity.Skill
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface Skills extends JpaRepository<Skill,Integer> {

    Optional<Skill> findBySkillName(String skillName);
}

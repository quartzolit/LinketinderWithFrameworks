package com.quartz.domain.repositories

import com.quartz.domain.entity.Candidate
import com.quartz.domain.entity.CandidateSkill
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CandidatesSkills extends JpaRepository<CandidateSkill,Integer>{

    void deleteByCandidate(Candidate candidate)

}
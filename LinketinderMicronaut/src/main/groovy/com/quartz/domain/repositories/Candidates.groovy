package com.quartz.domain.repositories

import com.quartz.domain.entity.Candidate
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface Candidates extends JpaRepository<Candidate,Integer>{

    Optional<Candidate> findByEmail(String email)

}
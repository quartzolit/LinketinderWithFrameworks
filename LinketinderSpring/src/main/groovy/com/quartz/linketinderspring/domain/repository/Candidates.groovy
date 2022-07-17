package com.quartz.linketinderspring.domain.repository

import com.quartz.linketinderspring.domain.entity.Candidate
import org.springframework.data.jpa.repository.JpaRepository

interface Candidates extends JpaRepository<Candidate,Integer>{

    Optional<Candidate> findByEmail(String email)

    Optional<Candidate> findByEmailAndPassword(String email, String password)

}
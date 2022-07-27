package com.quartz.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.ToString

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "candidates_skills")
class CandidateSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    Candidate candidate

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    Skill skill

    String level;
}

package com.mindtree.codindinChallenge1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.codindinChallenge1.entity.Team;


public interface TeamRepository extends JpaRepository<Team ,Integer>{

	Team getTeamByTeamName(String teamName);
}

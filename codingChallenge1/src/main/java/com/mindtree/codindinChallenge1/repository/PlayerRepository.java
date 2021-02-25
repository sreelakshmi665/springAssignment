package com.mindtree.codindinChallenge1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.codindinChallenge1.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

}

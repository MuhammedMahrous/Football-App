package com.mahrous.footballleaguemanager.model.dao;

import com.mahrous.footballleaguemanager.model.entity.Game;
import com.mahrous.footballleaguemanager.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Team> findByName(String name);
}

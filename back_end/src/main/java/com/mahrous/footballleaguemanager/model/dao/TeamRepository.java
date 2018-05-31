package com.mahrous.footballleaguemanager.model.dao;

import com.mahrous.footballleaguemanager.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    public Optional<Team> findByName(String name);
}

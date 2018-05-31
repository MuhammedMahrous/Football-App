package com.mahrous.footballleaguemanager.model.dao;

import com.mahrous.footballleaguemanager.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUserName(String userName);
}

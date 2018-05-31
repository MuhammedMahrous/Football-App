package com.mahrous.footballleaguemanager.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "team")
    private Collection<Player> teamPlayers;

    @OneToMany(mappedBy = "awayTeam")
    private Collection<Game> awayGames;

    @OneToMany(mappedBy = "homeTeam")
    private Collection<Game> homeGames;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(Collection<Player> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public Collection<Game> getAwayGames() {
        return awayGames;
    }

    public void setAwayGames(Collection<Game> awayGames) {
        this.awayGames = awayGames;
    }

    public Collection<Game> getHomeGames() {
        return homeGames;
    }

    public void setHomeGames(Collection<Game> homeGames) {
        this.homeGames = homeGames;
    }
}

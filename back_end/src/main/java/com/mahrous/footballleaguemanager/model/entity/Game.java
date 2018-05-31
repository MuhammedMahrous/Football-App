package com.mahrous.footballleaguemanager.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date gameDateTime;

    private String name;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    @JsonIgnoreProperties({"homeGames", "awayGames", "teamPlayers"})
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    @JsonIgnoreProperties({"homeGames", "awayGames", "teamPlayers"})
    private Team awayTeam;

    private int homeScore;

    private int awayScore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getGameDateTime() {
        return gameDateTime;
    }

    public void setGameDateTime(Date gameDateTime) {
        this.gameDateTime = gameDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int firstScore) {
        this.homeScore = firstScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int secondScore) {
        this.awayScore = secondScore;
    }
}

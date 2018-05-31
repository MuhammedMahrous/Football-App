package com.mahrous.footballleaguemanager.controller.rest;

import com.mahrous.footballleaguemanager.model.dao.PlayerRepository;
import com.mahrous.footballleaguemanager.model.dao.TeamRepository;
import com.mahrous.footballleaguemanager.model.entity.Player;
import com.mahrous.footballleaguemanager.model.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamApi {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public Collection<Team> teams() {
        return teamRepository.findAll();
    }

    @PostMapping("/create/{name}")
    public void createTeam(@PathVariable String name) {
        Optional<Team> optionalTeam = teamRepository.findByName(name);
        if (optionalTeam.isPresent()) {
            throw new RuntimeException("Team Name already exists; please user a different name");
            // TODO: Throw custom exception
        } else {
            Team team = new Team();
            team.setName(name);
            teamRepository.save(team);
        }

    }

    @PutMapping("/add/{playerId}/{teamId}")
    public void addPlayer(@PathVariable long playerId, @PathVariable long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);

        if (optionalPlayer.isPresent() && optionalTeam.isPresent()) {
            Player player = optionalPlayer.get();
            Team team = optionalTeam.get();
            player.setTeam(team);
            playerRepository.save(player);
        } else {
            throw new RuntimeException("Player or Team doesn't exist");
            // TODO: Throw custom exception
        }
    }

    @PutMapping("/edit/{id}")
    public void editTeam(@RequestBody Team team, @PathVariable Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            Team editedTeam = optionalTeam.get();
            editedTeam.setName(team.getName());
            teamRepository.save(editedTeam);
        } else {
            throw new RuntimeException("Team Doesn't exist");
            // TODO: Throw custom exception
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            teamRepository.deleteById(id);
        } else {
            throw new RuntimeException("Team Doesn't exist");
            // TODO: Throw custom exception
        }
    }
}

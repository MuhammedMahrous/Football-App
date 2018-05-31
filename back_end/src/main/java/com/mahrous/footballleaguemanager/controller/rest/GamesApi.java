package com.mahrous.footballleaguemanager.controller.rest;

import com.mahrous.footballleaguemanager.model.dao.GameRepository;
import com.mahrous.footballleaguemanager.model.dao.TeamRepository;
import com.mahrous.footballleaguemanager.model.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/games")
public class GamesApi {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public Collection<Game> games() {
        return gameRepository.findAll();
    }

    @PostMapping("/create/{homeId}/{awayId}")
    public void createGame(@RequestBody Game game, @PathVariable Long homeId, @PathVariable Long awayId) {
        if (teamRepository.existsById(homeId) && teamRepository.existsById(awayId)) {
            game.setAwayTeam(teamRepository.getOne(awayId));
            game.setHomeTeam(teamRepository.getOne(homeId));
            game.setHomeScore(0);
            game.setAwayScore(0);
            gameRepository.save(game);
        } else {
            throw new RuntimeException("One or both teams doesn't exist");
        }
    }


    @PutMapping("/updateScore/{gameId}/{homeScore}/{awayScore}")
    public void editTeam(@PathVariable Long gameId, @PathVariable int homeScore, @PathVariable int awayScore) {
        if (gameRepository.existsById(gameId)) {
            Game game = gameRepository.getOne(gameId);
            game.setHomeScore(homeScore);
            game.setAwayScore(awayScore);
            gameRepository.save(game);
        } else {
            throw new RuntimeException("Game doesn't exist");
            // TODO: Throw custom exception
        }
    }

    @DeleteMapping("/delete/{gameId}")
    public void deleteTeam(@PathVariable Long gameId) {
        if (gameRepository.existsById(gameId)) {
            gameRepository.deleteById(gameId);
        } else {
            throw new RuntimeException("Game Doesn't exist");
            // TODO: Throw custom exception
        }
    }
}

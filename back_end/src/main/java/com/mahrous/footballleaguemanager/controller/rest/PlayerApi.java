package com.mahrous.footballleaguemanager.controller.rest;

import com.mahrous.footballleaguemanager.model.dao.PlayerRepository;
import com.mahrous.footballleaguemanager.model.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerApi {
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping
    public Collection<Player> players() {
        return playerRepository.findAll();
    }

    @PostMapping("/create")
    public void createNewPlayer(@RequestBody Player player) {
        Optional<Player> optionalPlayer = playerRepository.findByUserName(player.getUserName());
        if (optionalPlayer.isPresent()) {
            //TODO: Throw Custom Exception
            throw new RuntimeException("Username already Exists");
        } else {
            playerRepository.save(player);
        }
    }

    @PutMapping("/edit/{id}")
    public void editPlayer(@RequestBody Player newPlayer, @PathVariable long id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isPresent()) {
            Player oldPlayer = playerOptional.get();
            oldPlayer.setName(newPlayer.getName());
            oldPlayer.setUserName(newPlayer.getUserName());
            playerRepository.save(oldPlayer);
        } else {
            //TODO: Throw Custom Exception
            throw new RuntimeException("Player doesn't exist!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayer(@PathVariable long id) {
        playerRepository.deleteById(id);
    }
}

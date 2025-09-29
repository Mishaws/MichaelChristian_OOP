package com.michael.backend.controller;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.michael.backend.model.Player;
import com.michael.backend.repository.PlayerRepository;
import com.michael.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RestController
    @RequestMapping

    @GetMapping
    public ResponseEntity<List<Player>>getAllPlayers(){
        List<Player> players = playerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayerById(@PathVariable UUID playerId) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        if (player.isPresent()) {
            return ResponseEntity.ok(player.get());
        } else {
            return new ResponseEntity.status(HttpStatus.NOT_FOUND);
            .body("\error\:\Player not found with username: " + username);
        }
    }


}

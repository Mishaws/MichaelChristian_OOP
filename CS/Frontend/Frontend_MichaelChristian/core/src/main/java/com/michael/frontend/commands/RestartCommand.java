package com.michael.frontend.commands;

import com.badlogic.gdx.Game;
import com.michael.frontend.GameManager;
import com.michael.frontend.Player;

public class RestartCommand {
    public Player player;
    public GameManager gameManager;

    public RestartCommand(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }

    public void execute() {
        player.reset();
        gameManager.setScore(0);
    }
}



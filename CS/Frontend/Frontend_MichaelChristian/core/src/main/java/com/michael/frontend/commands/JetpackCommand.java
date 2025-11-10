package com.michael.frontend.commands;

import com.michael.frontend.Player;

public class JetpackCommand implements Command {

    private Player player;

    public JetpackCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        if (player != null && !player.isDead()) {
            player.fly();
        }
    }
}

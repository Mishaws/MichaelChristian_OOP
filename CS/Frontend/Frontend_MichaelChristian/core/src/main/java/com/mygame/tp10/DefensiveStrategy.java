package com.mygame.tp10;

public class DefensiveStrategy implements IFightingStrategy {
    @Override
    public void execute(Character character) {
        System.out.println("LOG: Executing Defensive Strategy!");
        character.setCombatLog("DEFEND! Raise shield!");
    }

    @Override
    public String getName() {
        return "Defensive";
    }
}

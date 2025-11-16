package com.mygame.tp10;

public class AggressiveStrategy implements IFightingStrategy {
    @Override
    public void execute(Character character) {
        System.out.println("LOG: Executing Aggressive Strategy!");
        character.setCombatLog("ATTACK! Charge forward!");
    }

    @Override
    public String getName() {
        return "Aggressive";
    }
}

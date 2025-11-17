package com.mygame.tp10; // Pastikan package Anda benar

public class DefensiveStrategy implements IFightingStrategy {

    private static final int HEAL_AMOUNT = 20; // Jumlah HP yang disembuhkan

    @Override
    public void execute(Character character) {
        // Perbedaan signifikan: Menyembuhkan HP!
        character.heal(HEAL_AMOUNT);
        character.setCombatLog("HEAL! + " + HEAL_AMOUNT + " HP");
    }

    @Override
    public String getName() {
        return "Defensive";
    }
}

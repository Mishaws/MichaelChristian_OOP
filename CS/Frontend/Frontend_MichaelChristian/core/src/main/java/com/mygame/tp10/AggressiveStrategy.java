package com.mygame.tp10; // Pastikan package Anda benar

// (Pastikan nama file ini 'AggressiveStrategy.java' dengan dua 'g')
public class AggressiveStrategy implements IFightingStrategy {

    private static final float BUFF_DURATION = 3.0f; // 3 detik speed boost

    @Override
    public void execute(Character character) {
        // Perbedaan signifikan: Memberikan speed boost!
        character.applySpeedBuff(BUFF_DURATION);
        character.setCombatLog("SPEED BOOST AKTIF!");
    }

    @Override
    public String getName() {
        return "Aggressive";
    }
}

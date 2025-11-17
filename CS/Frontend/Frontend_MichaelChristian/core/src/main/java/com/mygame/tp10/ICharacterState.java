package com.mygame.tp10; // Pastikan package Anda benar

public interface ICharacterState {
    void handleInput(Character character);
    void update(Character character, float deltaTime);

    // Metode baru untuk mengatur warna berdasarkan state
    void applyTint(Character character);

    String getName();
}

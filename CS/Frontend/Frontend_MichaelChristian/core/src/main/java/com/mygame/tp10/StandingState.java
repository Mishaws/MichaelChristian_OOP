package com.mygame.tp10; // Pastikan package Anda benar

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class StandingState implements ICharacterState {
    @Override
    public void handleInput(Character character) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) ||
            Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            character.setState(new WalkingState());
        }
    }

    @Override
    public void update(Character character, float deltaTime) {
        // Hanya atur kecepatan jika tidak sedang di-buff
        if (!character.isBuffed()) {
            character.currentSpeed = 0;
        }
    }

    @Override
    public void applyTint(Character character) {
        // State berdiri = warna PUTIH
        character.tint.set(Color.WHITE);
    }

    @Override
    public String getName() {
        return "Standing";
    }
}

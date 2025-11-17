package com.mygame.tp10; // Pastikan package Anda benar

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class RunningState implements ICharacterState {
    @Override
    public void handleInput(Character character) {
        if (!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            character.setState(new WalkingState());
        }

        if (!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.A) &&
            !Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.D)) {

            character.setState(new StandingState());
        }
    }

    @Override
    public void update(Character character, float deltaTime) {
        // Hanya atur kecepatan jika tidak sedang di-buff
        if (!character.isBuffed()) {
            character.currentSpeed = character.RUN_SPEED;
        }

        // Logika pergerakan tetap berjalan, menggunakan kecepatan saat ini (bisa jadi speed buff)
        if (Gdx.input.isKeyPressed(Input.Keys.W)) character.position.y += character.currentSpeed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) character.position.y -= character.currentSpeed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) character.position.x -= character.currentSpeed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) character.position.x += character.currentSpeed * deltaTime;
    }

    @Override
    public void applyTint(Character character) {
        // State berlari = warna KUNING
        character.tint.set(Color.YELLOW);
    }

    @Override
    public String getName() {
        return "Running";
    }
}

package com.mygame.tp10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

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
        character.currentSpeed = 0;
    }

    @Override
    public String getName() {
        return "Standing";
    }
}

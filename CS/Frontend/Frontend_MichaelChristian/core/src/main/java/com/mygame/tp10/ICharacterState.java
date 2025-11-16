package com.mygame.tp10;

public interface ICharacterState {
    void handleInput(Character character);
    void update(Character character, float deltaTime);
    String getName();
}

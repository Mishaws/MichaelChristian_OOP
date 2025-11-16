package com.mygame.tp10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Character {

    public Vector2 position;
    private Texture texture;
    public final float WALK_SPEED = 100f;
    public final float RUN_SPEED = 250f;
    public float currentSpeed = 0f;

    private ICharacterState currentState;

    private IFightingStrategy currentStrategy;
    private String combatLog = "None";

    public Character(Texture texture) {
        this.texture = texture;
        this.position = new Vector2(100, 100);

        this.currentState = new StandingState();
        this.currentStrategy = new AggressiveStrategy();
    }

    public void update(float deltaTime) {
        currentState.handleInput(this);
        currentState.update(this, deltaTime);
        handleStrategyInput();
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    private void handleStrategyInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            this.setStrategy(new AggressiveStrategy());
            System.out.println("Strategy changed to Aggressive");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            this.setStrategy(new DefensiveStrategy());
            System.out.println("Strategy changed to Defensive");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.performCombatAction();
        }
    }

    public void setState(ICharacterState newState) {
        this.currentState = newState;
        System.out.println("State changed to: " + newState.getName());
    }

    public String getCurrentStateName() {
        return currentState.getName();
    }

    public void setStrategy(IFightingStrategy newStrategy) {
        this.currentStrategy = newStrategy;
    }

    public void performCombatAction() {
        this.currentStrategy.execute(this);
    }

    public String getCurrentStrategyName() {
        return currentStrategy.getName();
    }

    public void setCombatLog(String log) {
        this.combatLog = log;
    }

    public String getCombatLog() {
        return this.combatLog;
    }
}

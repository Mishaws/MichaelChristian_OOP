package com.michael.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.michael.frontend.strategies.DifficultyStrategy;

public class DifficultyTransitionState implements GameState {

    private final GameStateManager gsm;
    private final PlayingState playingState;
    private final DifficultyStrategy newStrategy;
    private final BitmapFont font;
    private float timer;

    public DifficultyTransitionState(GameStateManager gsm, PlayingState playingState, DifficultyStrategy newStrategy) {
        this.gsm = gsm;
        this.playingState = playingState;
        this.newStrategy = newStrategy;
        this.font = new BitmapFont();
        this.timer = 2.0f;
    }

    @Override
    public void update(float delta) {
        timer -= delta;

        if (timer <= 0) {
            playingState.setDifficulty(newStrategy);
            gsm.pop();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        playingState.render(batch);

        batch.begin();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        String text1 = "DIFFICULTY INCREASED!";
        String text2 = newStrategy.getClass().getSimpleName();

        font.draw(batch, text1, width / 2 - 100, height / 2 + 50);
        font.draw(batch, text2, width / 2 - 100, height / 2);

        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}

package com.michael.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState implements GameState {
    private final GameStateManager gsm;
    private final BitmapFont font;

    public GameOverState(GameStateManager gsm, BitmapFont font) {
        this.gsm = gsm;
        this.font = font;
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) {
            gsm.set(new PlayingState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        font.draw("GAME OVER", Gdx.graphics.getWidth(), getHeight());
        font.draw("Press SPACE to restart", Gdx.graphics.getWidth(), getHeight());
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}

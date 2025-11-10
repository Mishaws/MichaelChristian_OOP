package com.michael.frontend.observers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreUIObserver implements Observer {
    public BitmapFont font;
    public SpriteBatch batch;

    public ScoreUIObserver() {
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
    }

    public void render(int score) {
        float screenWidth = com.badlogic.gdx.Gdx.graphics.getWidth();
        float screenHeight = com.badlogic.gdx.Gdx.graphics.getHeight();
        float padding = 15.0f;

        batch.begin();
        font.draw(batch, "Score: " + score, screenWidth - 150f,  screenHeight - padding);
        batch.end();
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
    }

    @Override
    public void update(int score) {
        System.out.println("Updated Score: " + score);
    }
}

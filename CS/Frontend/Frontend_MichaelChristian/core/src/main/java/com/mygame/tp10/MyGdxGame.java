package com.mygame.tp10; // Pastikan package Anda benar

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private Character player;
    private Texture playerTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        playerTexture = createDummyTexture();
        player = new Character(playerTexture);
    }

    @Override
    public void render() {
        // Latar belakang baru yang lebih menarik (ungu tua)
        ScreenUtils.clear(0.2f, 0.1f, 0.3f, 1);

        float deltaTime = Gdx.graphics.getDeltaTime();

        player.update(deltaTime);

        batch.begin();

        player.render(batch);
        drawDebugUI();

        batch.end();
    }

    private void drawDebugUI() {
        // Menampilkan UI baru, termasuk Health
        font.draw(batch, "Health: " + player.getHealth() + " / " + player.getMaxHealth(), 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Current State: " + player.getCurrentStateName(), 10, Gdx.graphics.getHeight() - 30);
        font.draw(batch, "Current Strategy: " + player.getCurrentStrategyName(), 10, Gdx.graphics.getHeight() - 50);
        font.draw(batch, "Combat Log: " + player.getCombatLog(), 10, Gdx.graphics.getHeight() - 70);
        font.draw(batch, "Buff Timer: " + String.format("%.1f", player.getBuffTimer()), 10, Gdx.graphics.getHeight() - 90);


        font.draw(batch, "Controls:", 10, 100);
        font.draw(batch, "WASD: Move", 10, 80);
        font.draw(batch, "Hold SHIFT: Run", 10, 60);
        font.draw(batch, "1: Aggressive (Speed Boost)", 10, 40);
        font.draw(batch, "2: Defensive (Heal)", 10, 20);
        font.draw(batch, "SPACE: Execute Strategy", Gdx.graphics.getWidth() - 200, 20);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        playerTexture.dispose();
    }

    private Texture createDummyTexture() {
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
}

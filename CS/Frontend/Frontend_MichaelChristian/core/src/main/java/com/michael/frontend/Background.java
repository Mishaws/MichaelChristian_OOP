package com.michael.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
    public Texture backgroundTexture;
    public TextureRegion backgroundRegion;
    public float height;
    public float width;
    public float currentCameraX = 0f;

    public Background(Texture backgroundTexture, TextureRegion backgroundRegion, float height, float width, float currentCameraX) {
        this.backgroundTexture = new Texture("background.png");
        this.backgroundRegion = new TextureRegion(this.backgroundTexture);
        this.width = 2688f;
        this.height = 1536f;
    }

    public void update(float cameraX) {
        this.currentCameraX = cameraX;
    }

    public void render(SpriteBatch batch) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float screenScale = screenHeight / this.height;
        float scaledWidth = this.width * screenScale;
        float scaledHeight = this.height * screenScale;

        float viewX = this.currentCameraX - (screenWidth / 2);

        float startDrawX = (float)Math.floor(viewX / scaledWidth) * scaledWidth;

        float endDrawX = viewX + screenWidth;
        float currentDrawX = startDrawX;
        while (currentDrawX < endDrawX) {
            batch.draw(
                this.backgroundRegion,
                currentDrawX,
                0f,
                scaledWidth,
                scaledHeight
            );

            currentDrawX += scaledWidth;
        }
    }

    public void dispose() {
        backgroundTexture.dispose();
    }
}

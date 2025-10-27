package com.michael.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class TP7_DemoGame extends ApplicationAdapter {

    private ShapeRenderer shapeRenderer;
    private Rectangle box;
    private Color boxColor;
    private int colorState;

    private final float BOX_SIZE = 100;
    private final float BOX_SPEED = 300;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        float startX = (Gdx.graphics.getWidth() - BOX_SIZE) / 2f;
        float startY = (Gdx.graphics.getHeight() - BOX_SIZE) / 2f;
        box = new Rectangle(startX, startY, BOX_SIZE, BOX_SIZE);

        boxColor = Color.RED.cpy();
        colorState = 0;
    }

    @Override
    public void render() {
        handleInput();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            changeColor();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(boxColor);
        shapeRenderer.rect(box.x, box.y, box.width, box.height);
        shapeRenderer.end();
    }

    private void handleInput() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        float moveAmount = BOX_SPEED * deltaTime;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            box.x -= moveAmount;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            box.x += moveAmount;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            box.y -= moveAmount;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            box.y += moveAmount;
        }

        if (box.x < 0) {
            box.x = 0;
        }

        if (box.x + box.width > Gdx.graphics.getWidth()) {
            box.x = Gdx.graphics.getWidth() - box.width;
        }

        if (box.y < 0) {
            box.y = 0;
        }

        if (box.y + box.height > Gdx.graphics.getHeight()) {
            box.y = Gdx.graphics.getHeight() - box.height;
        }
    }

    private void changeColor() {
        colorState = (colorState + 1) % 3;

        String colorName;

        switch (colorState) {
            case 0:
                boxColor = Color.RED.cpy();
                colorName = "Merah (Red)";
                break;
            case 1:
                boxColor = Color.YELLOW.cpy();
                colorName = "Kuning (Yellow)";
                break;
            case 2:
                boxColor = Color.BLUE.cpy();
                colorName = "Biru (Blue)";
                break;
            default:
                boxColor = Color.RED.cpy();
                colorName = "Merah (Red)";
                colorState = 0;
                break;
        }
        Gdx.app.log("Warna Diganti", "Warna kotak di-set ke: " + colorName);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}

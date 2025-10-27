package com.michael.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Player player;
    private Ground ground;
    private GameManager gameManager;

    private OrthographicCamera camera;
    private float cameraOffset = 0.2f;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        gameManager = GameManager.getInstance();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);

        Vector2 startPos = new Vector2(100, Gdx.graphics.getHeight() / 2f);
        player = new Player(startPos);

        ground = new Ground();

        gameManager.startGame();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        update(delta);

        ScreenUtils.clear(0.1f, 0.1f, 0.2f, 1f);

        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        ground.renderShape(shapeRenderer);
        player.renderShape(shapeRenderer);

        shapeRenderer.end();
    }

    private void update(float delta) {
        boolean isFlying = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        player.update(delta, isFlying);
        updateCamera(delta);
        ground.update(camera.position.x);

        player.checkBoundaries(ground, camera.viewportHeight);

        int newScore = (int) player.getDistanceTraveled();
        if (newScore != gameManager.getScore()) {
            gameManager.setScore(newScore);
        }
    }

    private void updateCamera(float delta) {
        float cameraFocus = player.getPosition().x + (Gdx.graphics.getWidth() * cameraOffset);

        camera.position.x = cameraFocus;

        camera.update();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}

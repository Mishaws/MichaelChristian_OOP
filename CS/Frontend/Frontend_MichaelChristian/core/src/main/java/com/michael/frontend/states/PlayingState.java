package com.michael.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.michael.frontend.Background;
import com.michael.frontend.GameManager;
import com.michael.frontend.Ground;
import com.michael.frontend.Player;
import com.michael.frontend.commands.Command;
import com.michael.frontend.commands.JetpackCommand;
import com.michael.frontend.commands.RestartCommand;
import com.michael.frontend.factories.ObstacleFactory;
import com.michael.frontend.observers.ScoreUIObserver;
import com.michael.frontend.obstacles.BaseObstacle;
import com.michael.frontend.obstacles.HomingMissile;
import com.michael.frontend.strategies.DifficultyStrategy;
import com.michael.frontend.strategies.EasyDifficultyStrategy;
import com.michael.frontend.strategies.HardDifficultyStrategy;
import com.nama.frontend.strategies.MediumDifficultyStrategy;

public class PlayingState implements GameState {

    private final GameStateManager gsm;
    private ShapeRenderer shapeRenderer;

    private Player player;
    private Ground ground;
    private GameManager gameManager;
    private Background background;
    private Command jetpackCommand;
    private Command restartCommand;
    private ScoreUIObserver scoreUIObserver;
    private ObstacleFactory obstacleFactory;
    private float obstacleSpawnTimer;
    private float lastObstacleSpawnX = 0f;

    private static float OBSTACLE_SPAWN_INTERVAL = 2.5f;
    private static int OBSTACLE_DENSITY = 1;
    private static float SPAWN_AHEAD_DISTANCE = 300f;
    private static float MIN_OBSTACLE_GAP = 200f;
    private static final float OBSTACLE_CLUSTER_SPACING = 250f;

    private OrthographicCamera camera;
    private float cameraOffset = 0.2f;
    private int screenWidth;
    private int screenHeight;
    private int lastLoggedScore = -1;

    private DifficultyStrategy difficultyStrategy;

    public PlayingState(GameStateManager gsm) {
        this.gsm = gsm;

        shapeRenderer = new ShapeRenderer();
        this.gameManager = GameManager.getInstance();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);

        player = new Player(new Vector2(100, screenHeight / 2f));
        ground = new Ground();

        jetpackCommand = new JetpackCommand(player);
        restartCommand = new RestartCommand(player, gameManager);

        scoreUIObserver = new ScoreUIObserver();
        gameManager.addObserver(scoreUIObserver);

        background = new Background();

        obstacleFactory = new ObstacleFactory();
        obstacleSpawnTimer = 0f;

        gameManager.startGame();

        setDifficulty(new EasyDifficultyStrategy());
    }

    public void setDifficulty(DifficultyStrategy newStrategy) {
        this.difficultyStrategy = newStrategy;

        obstacleFactory.setWeights(newStrategy.getObstacleWeights());

        OBSTACLE_SPAWN_INTERVAL = newStrategy.getSpawnInterval();
        OBSTACLE_DENSITY = newStrategy.getDensity();
        MIN_OBSTACLE_GAP = newStrategy.getMinGap();

        System.out.println("Difficulty changed to: " + newStrategy.getClass().getSimpleName());
    }

    @Override
    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            jetpackCommand.execute();
        }

        if (player.isDead()) {
            gsm.set(new GameOverState(gsm));
            return;
        }

        player.update(delta, false);
        updateCamera(delta);

        background.update(camera.position.x);
        ground.update(camera.position.x);
        player.checkBoundaries(ground, screenHeight);

        updateObstacles(delta);
        checkCollisions();

        // Logika skor dari Main.update()
        int currentScoreMeters = (int)player.getDistanceTraveled();
        int previousScoreMeters = gameManager.getScore();

        if (currentScoreMeters > previousScoreMeters) {
            if (currentScoreMeters != lastLoggedScore) {
                System.out.println("Distance: " + currentScoreMeters + "m");
                lastLoggedScore = currentScoreMeters;
            }
            gameManager.setScore(currentScoreMeters);
        }
        updateDifficulty(currentScoreMeters);
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        background.render(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        ground.renderShape(shapeRenderer);
        player.renderShape(shapeRenderer);

        shapeRenderer.setColor(Color.RED);
        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {
            obstacle.render(shapeRenderer);
        }

        shapeRenderer.end();

        scoreUIObserver.render(gameManager.getScore());
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        obstacleFactory.releaseAllObstacles();
        scoreUIObserver.dispose();
        background.dispose();
    }

    private void updateDifficulty(int score) {

        if (score > 1000 && !(difficultyStrategy instanceof HardDifficultyStrategy)) {
            gsm.push(new DifficultyTransitionState(gsm, this, new HardDifficultyStrategy()));

        } else if (score > 2000 && !(difficultyStrategy instanceof MediumDifficultyStrategy)) {
            gsm.push(new DifficultyTransitionState(gsm, this, new MediumDifficultyStrategy()));
        }
    }

    private void updateCamera(float delta) {
        float cameraFocus = player.getPosition().x + screenWidth * cameraOffset;
        camera.position.x = cameraFocus;
        camera.update();
    }

    private void updateObstacles(float delta) {
        obstacleSpawnTimer += delta;

        if (obstacleSpawnTimer >= OBSTACLE_SPAWN_INTERVAL) {
            spawnObstacle();
            obstacleSpawnTimer = 0f;
        }

        float cameraLeftEdge = camera.position.x - screenWidth / 2f;

        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {
            if (obstacle instanceof HomingMissile) {
                ((HomingMissile) obstacle).setTarget(player);
                ((HomingMissile) obstacle).update(delta);
            }

            if (obstacle.isOffScreenCamera(cameraLeftEdge)) {
                obstacleFactory.releaseObstacle(obstacle);
            }
        }
    }

    private void spawnObstacle() {
        float cameraRightEdge = camera.position.x + screenWidth / 2f;
        float spawnAheadOfCamera = cameraRightEdge + SPAWN_AHEAD_DISTANCE;
        float spawnAfterLastObstacle = lastObstacleSpawnX + MIN_OBSTACLE_GAP;

        float baseSpawnX = Math.max(spawnAheadOfCamera, spawnAfterLastObstacle);

        for (int i = 0; i < OBSTACLE_DENSITY; i++) {
            float spawnX = baseSpawnX + (i * OBSTACLE_CLUSTER_SPACING);
            obstacleFactory.createRandomObstacle(ground.getTopY(), spawnX, player.getHeight());
            lastObstacleSpawnX = spawnX;
        }
    }

    private void checkCollisions() {
        Rectangle playerCollider = player.getCollider();
        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {
            if (obstacle.isColliding(playerCollider)) {
                player.die();
                return;
            }
        }
    }
}

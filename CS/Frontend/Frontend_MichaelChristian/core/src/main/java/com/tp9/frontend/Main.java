package com.tp9.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Player player;
    private ScoreSystem scoreSystem;
    private ScoreDisplay scoreDisplay;
    private Map<Integer, Command> inputCommands;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        scoreSystem = new ScoreSystem();
        scoreDisplay = new ScoreDisplay();
        scoreSystem.registerObserver(scoreDisplay);
        inputCommands = new HashMap<>();
        inputCommands.put(Input.Keys.W, new MoveCommand(player, 0, 5));
        inputCommands.put(Input.Keys.A, new MoveCommand(player, -5, 0));
        inputCommands.put(Input.Keys.S, new MoveCommand(player, 0, -5));
        inputCommands.put(Input.Keys.D, new MoveCommand(player, 5, 0));
        System.out.println("Press W,A,S,D to Move. Press SPACE to add score.");
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            inputCommands.get(Input.Keys.W).execute();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            inputCommands.get(Input.Keys.A).execute();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            inputCommands.get(Input.Keys.S).execute();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            inputCommands.get(Input.Keys.D).execute();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            scoreSystem.addScore(10);
        }

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.circle(player.getPosition().x, player.getPosition().y, 20);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    public interface ScoreObserver {
        void onScoreUpdate(int newScore);
    }

    public class ScoreSystem {
        private ArrayList<ScoreObserver> observers;
        private int score;
        public ScoreSystem() {
            this.observers = new ArrayList<>();
            this.score = 0;
        }
        public void registerObserver(ScoreObserver observer) {
            observers.add(observer);
        }
        public void removeObserver(ScoreObserver observer) {
            observers.remove(observer);
        }
        public void notifyObservers() {
            for (ScoreObserver observer : observers) {
                observer.onScoreUpdate(score);
            }
        }
        public void addScore(int amount) {
            this.score += amount;
            notifyObservers();
        }
    }

    public class ScoreDisplay implements ScoreObserver {
        @Override
        public void onScoreUpdate(int newScore) {
            System.out.println("SKOR BARU: " + newScore);
        }
    }

    public class Player {
        private Vector2 position;
        public Player(float x, float y) {
            this.position = new Vector2(x, y);
        }
        public void move(float dx, float dy) {
            this.position.add(dx, dy);
        }
        public Vector2 getPosition() {
            return position;
        }
    }

    public interface Command {
        void execute();
    }

    public class MoveCommand implements Command {
        private Player player;
        private float dx;
        private float dy;
        public MoveCommand(Player player, float dx, float dy) {
            this.player = player;
            this.dx = dx;
            this.dy = dy;
        }
        @Override
        public void execute() {
            player.move(dx, dy);
        }
    }
}

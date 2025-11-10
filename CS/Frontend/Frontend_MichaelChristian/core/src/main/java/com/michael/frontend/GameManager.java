package com.michael.frontend;

import com.michael.frontend.observers.Observer;
import com.michael.frontend.observers.ScoreManager;

public class GameManager {
    private static GameManager instance;

    private ScoreManager scoreManager;
    private boolean gameActive;

    private GameManager() {
        scoreManager = new ScoreManager();
        gameActive = false;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame() {
        scoreManager = new ScoreManager();
        gameActive = true;
        System.out.println("Game Started!");
    }

    public void setScore(int newScore) {
        if (gameActive) {
            scoreManager.setScore(newScore);
        }
    }

    // Getters
    public int getScore() {
        return scoreManager.getScore();
    }

    public void addObserver(Observer observer) {
        this.scoreManager.addObserver(observer);
    }

    public boolean removeObserver(Observer observer) {
        return this.scoreManager.removeObserver(observer);
    }
}

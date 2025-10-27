package com.michael.frontend;

public class GameManager {
    final static GameManager instance = new GameManager();
    private int score;
    private boolean gameActive;


    public GameManager() {
        int score = 0;
        boolean gameActive = false;
    }
}



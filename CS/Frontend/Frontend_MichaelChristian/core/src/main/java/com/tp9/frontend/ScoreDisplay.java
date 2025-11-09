package com.tp9.frontend;

public class ScoreDisplay implements ScoreObserver {
    @Override
    public void onScoreUpdate(int newScore) {
        System.out.println("New Score: " + newScore);
    }
}

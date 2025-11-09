package com.tp9.frontend;

import java.util.ArrayList;

public class ScoreSystem {
    private ArrayList<ScoreObserver> observers;
    private int score;

    public ScoreSystem(){
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

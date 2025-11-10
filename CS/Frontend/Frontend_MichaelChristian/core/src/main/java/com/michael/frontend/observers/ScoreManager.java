package com.michael.frontend.observers;
import java.util.ArrayList;

public class ScoreManager implements Subject {
    public ArrayList<Observer> observers = new ArrayList<>();
    public int score;

    public ScoreManager() {
        observers = new ArrayList<>();
        score = 0;
    }

    public void setScore(int newScore) {
        if (newScore != this.score) {
            this.score = newScore;
            notifyObservers(score);
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public boolean removeObserver(Observer observer) {
        observers.remove(observer);
        return false;
    }

    @Override
    public void notifyObservers(int score) {
        for (Observer observer : observers) {
            observer.update(score);
        }
    }
}

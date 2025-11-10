package com.michael.frontend.observers;

import com.tp9.frontend.ScoreObserver;

import java.util.ArrayList;

public class ScoreManager implements Subject {
    public ArrayList<Observer> observers = new ArrayList<>();
    public int score;

    public ScoreManager() {
        observers = new ArrayList<>();
        score = 0;
    }

    void addScore(int amount){
        notifyObservers();
    }



    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int score) {
        for (Observer observer : observers) {
            observer.onScoreUpdate();
        }
    }
}

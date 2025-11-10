package com.michael.frontend.observers;

import com.tp9.frontend.ScoreObserver;

public class ScoreDisplay implements ScoreObserver {
    @Override
    public void onScoreUpdate(int newScore) {
        notifyObservers();
    }
}

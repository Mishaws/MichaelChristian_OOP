package com.michael.frontend.observers;

public interface Subject {
    default void addObserver(Observer observer) {

    }
    default boolean removeObserver(Observer observer) {

        return false;
    }
    default void notifyObservers(int score) {

    }
}

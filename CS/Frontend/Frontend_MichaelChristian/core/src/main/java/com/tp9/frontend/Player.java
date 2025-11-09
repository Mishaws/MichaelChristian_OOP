package com.tp9.frontend;

import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;

    public Player(float x, float y) {
        this.position = new Vector2(x, y);
    }

    public void move(float dx, float dy) {
        this.position.add(dx, dy);
    }

    public Vector2 getPosition(){
        return position;
    }
}

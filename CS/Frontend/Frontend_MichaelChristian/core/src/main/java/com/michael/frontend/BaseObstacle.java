package com.michael.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseObstacle {
    private Vector2 position;
    private Rectangle collider;
    private float length;
    private final float WIDTH = 10f;
    private boolean active = false;

    public BaseObstacle(Vector2 startPosition, int length){
        this.position = startPosition;
        this.length = length;
        updateCollider();
    }

    public void initialize(Vector2 startPosition, int length){
        this.position = startPosition;
        this.length = length;
        updateCollider();
    }

    public void render(ShapeRenderer shapeRenderer) {
        if(active) {
            drawShape(shapeRenderer);
        }
    }

    public boolean isColliding(Rectangle playerCollider) {
        if(active && collider.overlaps(playerCollider)) {
            return active;
        }
        return false;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isOffScreenCamera(float cameraLeftEdge) {
        if (cameraLeftEdge < getRenderWidth()) {
            return true;
        }
        return false;
    }

    private void updateCollider(){

    }

    abstract void drawShape(ShapeRenderer shapeRenderer);
    abstract float getRenderWidth();
    abstract float getRenderHeight();

    public void setActive(boolean active) {
        this.active = active;
    }
    public void setPosition(float x, float y) {
        this.position = position;
    }

    public Vector2 getPosition(){
        return position;
    }
}

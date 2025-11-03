package com.michael.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.michael.frontend.Player;

public class HomingMissile extends BaseObstacle {

    private Player target;
    private Vector2 velocity;
    private float speed = 200f;
    private float width = 40f;
    private float height = 20f;

    public HomingMissile(Vector2 startPosition) {
        super(startPosition, 0);
        this.velocity = new Vector2();
    }

    @Override
    public void initialize(Vector2 startPosition, int length) {
        super.initialize(startPosition, length);
        this.velocity.set(0, 0);
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public boolean isTargetingPlayer() {
        if (target == null) {
            return false;
        }

        float targetCenterX = target.getCollider().x + (target.getCollider().width / 2);
        float missileCenterX = this.position.x + (this.width / 2);

        return missileCenterX >= targetCenterX;
    }

    public void update(float delta) {
        if (target == null || !active) {
            return;
        }

        if (isTargetingPlayer()) {
            Vector2 targetPosition = target.getPosition();

            velocity.set(targetPosition).sub(position).nor().scl(speed);

            position.x += velocity.x * delta;
            position.y += velocity.y * delta;

            updateCollider();
        }
    }

    @Override
    protected void updateCollider() {
        if (this.collider == null) {
            this.collider = new Rectangle(position.x, position.y, width, height);
        } else {
            this.collider.set(position.x, position.y, width, height);
        }
    }

    @Override
    protected void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, width, height);
    }

    @Override
    protected float getRenderWidth() {
        return width;
    }

    @Override
    protected float getRenderHeight() {
        return height;
    }
}

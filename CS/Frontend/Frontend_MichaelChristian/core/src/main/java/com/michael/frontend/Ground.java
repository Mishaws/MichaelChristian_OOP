package com.michael.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Rectangle;

public class Ground {
    final static float GROUND_HEIGHT = 50f;
    private Rectangle collider;

    public Ground(){
        Rectangle collider = new Rectangle(0,0);
        collider = Gdx.graphics.getWidth();
    }
}

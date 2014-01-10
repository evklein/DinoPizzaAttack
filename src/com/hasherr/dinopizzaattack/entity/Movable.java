package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 1/8/14
 */
public interface Movable
{
    // Implements velocity for movable entities.
    Vector2 velocity = new Vector2(1.0);

    // Animates the player.
    public abstract void animate();

    // Getters for animation purposes.
    public abstract float getLeftOffset();
    public abstract float getRightOffset();
}

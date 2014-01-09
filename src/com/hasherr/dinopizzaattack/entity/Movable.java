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
}

package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */
public interface Shoot // Behavior which allows entities to fire projectiles.
{
    public abstract void shoot(Vector2 direction);

}

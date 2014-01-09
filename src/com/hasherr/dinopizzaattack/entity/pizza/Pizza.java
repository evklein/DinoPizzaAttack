package com.hasherr.dinopizzaattack.entity.pizza;

import com.hasherr.dinopizzaattack.entity.Entity;
import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 1/8/14
 */
public abstract class Pizza extends Entity
{
    public Pizza(Vector2 pos, Sprite pizzaSprite)
    {
        super(pos, pizzaSprite, null);
    }
}

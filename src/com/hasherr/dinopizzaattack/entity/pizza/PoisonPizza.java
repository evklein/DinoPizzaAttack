package com.hasherr.dinopizzaattack.entity.pizza;

import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 1/8/14
 */
public class PoisonPizza extends Pizza
{
    public PoisonPizza(Vector2 pos)
    {
        super(pos, new Sprite(1f, TextureHandler.getTexture("/pizza/poison_pizza", "Png")));
    }
    @Override
    public void update()
    {

    }
}

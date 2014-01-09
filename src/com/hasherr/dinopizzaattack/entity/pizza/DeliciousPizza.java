package com.hasherr.dinopizzaattack.entity.pizza;

import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 1/8/14
 */
public class DeliciousPizza extends Pizza
{
    public DeliciousPizza(Vector2 pos)
    {
        super(pos, new Sprite(1f, TextureHandler.getTexture("/pizza/pizza", "Png")));
    }

    // Update the pizza on the screen.
    @Override
    public void update()
    {

    }
}

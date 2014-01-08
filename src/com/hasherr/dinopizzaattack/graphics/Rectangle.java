package com.hasherr.dinopizzaattack.graphics;

import com.hasherr.dinopizzaattack.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 1/6/14
 */
public class Rectangle
{
    Vector2 pos;
    double width;
    double height;

    public Rectangle(Vector2 pos, double width, double height)
    {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public void update(Vector2 pos, double width, double height)
    {

    }

    public boolean collidesWith(Rectangle rectangle)
    {
         return false;
    }

}

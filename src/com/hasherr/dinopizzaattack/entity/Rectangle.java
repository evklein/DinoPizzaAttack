package com.hasherr.dinopizzaattack.entity;

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
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(Rectangle rectangle)
    {
        if (this.pos.x + this.width < rectangle.pos.x ||
                rectangle.pos.x + rectangle.width < this.pos.x ||
                this.pos.y + this.height < rectangle.pos.y ||
                rectangle.pos.y + rectangle.height < this.pos.y)
        {
            return false;
        }
        else
        {
            System.out.println("Collides.");
            return true;
        }
    }

}

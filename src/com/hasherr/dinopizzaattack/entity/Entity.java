package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.math.Vector2;
import org.newdawn.slick.opengl.Texture;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public abstract class Entity
{
    Vector2 pos;

    public abstract void move(Direction dir);
    public abstract Texture getSprite();
    public abstract void draw();
}

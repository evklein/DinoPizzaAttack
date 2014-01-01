package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.math.Vector2;
import org.newdawn.slick.opengl.Texture;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public abstract class Entity
{
    // Every entity has a position vector.
    public Vector2 pos;

    // Method which returns a Sprite's texture for drawing purposes.
    public abstract Texture getSprite();

    // Draws the entity to the screen on an OpenGL quad.
    public abstract void draw();

    // Updates the entity's position and variables at a constant rate.
    public abstract void update();
}

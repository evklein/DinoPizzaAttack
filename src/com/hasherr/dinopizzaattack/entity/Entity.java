package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.graphics.Rectangle;
import com.hasherr.dinopizzaattack.graphics.Sprite;
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
    Vector2 velocity;
    Direction orientation;

    Rectangle boundingBox;
    Sprite sprite;

    public Entity(Vector2 pos, Sprite sprite)
    {
        this.pos = pos;
        this.sprite = sprite;
        this.velocity = new Vector2(1.0);
        this.orientation = Direction.EAST; // Initial value is EAST.

        boundingBox = new Rectangle(pos, sprite.getWidth(), sprite.getHeight());
    }

    // Method which returns the entities sprite texture for drawing purposes.
    public Texture getSprite()
    {
        return sprite.getTexture();
    }

    // Update the entities existing bounding box location and dimensions.
    public void updateBoundingBox()
    {
        boundingBox.update(pos, sprite.getWidth(), sprite.getHeight());
    }

    // Draws the entity to the screen on an OpenGL quad.
    public void draw()
    {
        sprite.draw(pos, velocity, orientation);
    }

    // Updates the entity's position and variables at a constant rate.
    public abstract void update();
}

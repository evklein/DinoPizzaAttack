package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
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
    public Vector2 pos;
    Direction orientation;

    Rectangle boundingBox;
    Sprite sprite;

    public Entity(Vector2 pos, Sprite sprite, Direction initDirection)
    {
        this.pos = pos;
        this.sprite = sprite;
        this.orientation = initDirection;

        boundingBox = new Rectangle(pos, sprite.getWidth(), sprite.getHeight());
    }

    // Get and set the orientation of the sprite (if there is an orientation).
    public void setOrientation(Direction orientation) { this.orientation = orientation; }
    public Direction getOrientation() { return orientation; }

    // Get and set the bounding box of the entity.
    public void setBoundingBox(Rectangle boundingBox) { this.boundingBox = boundingBox; }
    public Rectangle getBoundingBox() { return this.boundingBox; }

    // Method which returns the entities sprite texture for drawing purposes.
    public Texture getSprite() { return sprite.getTexture(); }

    // Update the entities existing bounding box location and dimensions.
    protected void updateBoundingBox() { boundingBox.update(pos, sprite.getWidth(), sprite.getHeight()); }

    // Draws the entity to the screen on an OpenGL quad.
    public void draw(float numOfSprites, float leftOffset, float rightOffset)
    {
        sprite.draw(pos, leftOffset, rightOffset);
    }

    // Updates the entity's position and variables at a constant rate.
    public abstract void update();
}

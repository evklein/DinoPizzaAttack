package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.projectile.Laser;
import com.hasherr.dinopizzaattack.entity.projectile.Shoot;
import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */
public class Player extends Entity implements Shoot
{
    // In-game attributes.
    boolean isAlive;
    int health;
    int score;

    // Player constructor.
    public Player(double x, double y)
    {
        super(new Vector2(x, y), new Sprite(8f, TextureHandler.getTexture("dino_spritesheet", "png")),
                Direction.EAST);

        isAlive = true;
        health = 100;
        score = 0;
    }

    // Update the player.
    @Override
    public void update()
    {
        updateBoundingBox();

        // Create friction for the player, allow for acceleration/deceleration.
        velocity.x *= 0.84f;
        velocity.y *= 0.84f;

        // Update the player's position.
        pos.x += velocity.x;
        pos.y += velocity.y;

        handleCollision();
    }

    /*
     * Player movement, collision, shooting, and other things to handle.
     */

    // Sets the player's face direction for animation purposes.
    private void setFaceDirection(Direction dir)
    {
        if (dir == Direction.NORTH || dir == Direction.SOUTH)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            orientation = dir;
        }
    }

    // Gets player input and uses it to move the player.
    public void move(Direction dir)
    {
        double moveSpeed = 60 * Game.getDeltaTime();

        if (dir == Direction.NORTH)
        {
            velocity.y += moveSpeed;
        }
        if (dir == Direction.SOUTH)
        {
            velocity.y += -moveSpeed;
        }
        if (dir == Direction.EAST)
        {
            setFaceDirection(dir);
            velocity.x += moveSpeed;
        }
        if (dir == Direction.WEST)
        {
            setFaceDirection(dir);
            velocity.x += -moveSpeed;
        }
    }

    // Handle player collision with outside boundaries.
    public void handleCollision()
    {
        float numOfSprites = 8f;
        if (pos.x <= 0) // Detect and handle collision on the far left side.
        {
            pos.x = 0;
            velocity.x = 1.0;
        }
        if (pos.x + (sprite.getWidth() / 8f) >= Game.WIDTH) // Detect and handle collision on the far right side.
        {
            pos.x = Game.WIDTH - (sprite.getWidth() / numOfSprites);
            velocity.x = 1.0;
        }
        if (pos.y <= 0) // Detect and handle collision on the bottom side.
        {
            pos.y = 0;
            velocity.y = 1.0;
        }
        if (pos.y + sprite.getHeight() >= Game.HEIGHT) // Detect and handle collision on the top side.
        {
            pos.y = Game.HEIGHT - sprite.getHeight();
            velocity.y = 1.0;
        }
    }

    // Fires a single projectile in the direction of the mouse.
    @Override
    public void shoot(Vector2 direction)
    {
        Laser projectile = new Laser(this, direction);
    }
}

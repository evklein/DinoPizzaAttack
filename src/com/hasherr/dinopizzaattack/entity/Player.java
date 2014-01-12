package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.projectile.Laser;
import com.hasherr.dinopizzaattack.entity.projectile.Shoot;
import com.hasherr.dinopizzaattack.graphics.AnimationTool;
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
public class Player extends Entity implements Shoot, Movable
{
    AnimationTool animationTool;

    // In-game attributes.
    boolean isAlive;
    int health;
    int score;

    // Player constructor.
    public Player(double x, double y)
    {
        super(new Vector2(x, y), new Sprite(8f, TextureHandler.getTexture("dino_spritesheet", "png")),
                Direction.EAST);
        animationTool = new AnimationTool(8f);
        isAlive = true;
        health = 100;
        score = 0;
    }

    // Update the player.
    @Override
    public void update()
    {
        animate();

        // Create friction for the player, allow for acceleration/deceleration.
        velocity.x *= 0.84f;
        velocity.y *= 0.84f;

        // Update the player's position.
        pos.x += velocity.x;
        pos.y += velocity.y;

        updateBoundingBox();
        handleCollisionOnWall();
    }

    /*
     * Player movement, collision, shooting, and other things to handle.
     */

    // Sets the player's face direction for animation purposes.
    private void setFaceDirection(Direction dir) throws IllegalArgumentException
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
        double moveSpeed = 75 * Game.getDeltaTime();

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
    public void handleCollisionOnWall()
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

    public void takeDamage()
    {
        if (health > 0)
        {
            health -= 1;
            System.out.println("Health: " + health);
        }
        else
        {
            System.exit(0);
        }
    }

    @Override
    public void animate()
    {
        // Stop animating if the player stops moving.
        final double STOP_SPEED = 1.0;
        if (velocity.x <= STOP_SPEED && velocity.y <= STOP_SPEED &&
                velocity.x >= -STOP_SPEED && velocity.y >= -STOP_SPEED)
        {
            animationTool.doAnimation(null);
        }
        else
        {
            animationTool.doAnimation(orientation);
        }
    }

    @Override public float getLeftOffset() { return animationTool.getLeftOffset(); }
    @Override public float getRightOffset() { return animationTool.getRightOffset(); }
}

package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;
import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */
public class Player extends Entity implements Shoot
{
    Vector2 velocity;
    Texture playerSprite = TextureHandler.getTexture("stego", "png");

    // Player constructor.
    public Player(int x, int y)
    {
        pos = new Vector2(x, y);
        velocity = new Vector2(0, 0);

    }

    private void handleCollision()
    {
        if (pos.x >= Game.width - (playerSprite.getImageWidth() + 2))
        {
            pos.x = Game.width;
        }
        if (pos.x <= 0)
        {
            pos.x = 0;
        }
        if (pos.y >= Game.height)
        {
            pos.y = Game.height;
        }
        if (pos.y <= 1)
        {
            pos.y = 1;
        }
    }

    // Move method so that player can change positions in 2.5D.
    public void move(Direction dir)
    {
        if (dir == Direction.NORTH)
        {
            velocity.y += 60 * Game.delta;
            handleCollision();
        }
        if (dir == Direction.SOUTH)
        {
            velocity.y += -60f * Game.delta;
            handleCollision();
        }
        if (dir == Direction.EAST)
        {
            velocity.x += 60f * Game.delta;
            handleCollision();
        }
        if (dir == Direction.WEST)
        {
            velocity.x += -60f * Game.delta;
            handleCollision();
        }
    }

    // Fires a single projectile in the direction of the mouse.
    @Override
    public void shoot(Vector2 direction)
    {
        Laser projectile = new Laser(this, direction);
    }

    @Override
    public Texture getSprite()
    {
         return playerSprite;
    }

    @Override
    public void draw() // Draw the player sprite onto the screen.
    {

        playerSprite.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 1.0f);
        glVertex2d(pos.x, pos.y);

        glTexCoord2f(1.0f, 1.0f);
        glVertex2d(pos.x + playerSprite.getImageWidth(), pos.y);

        glTexCoord2f(1.0f, 0.0f);
        glVertex2d(pos.x + playerSprite.getImageWidth(), pos.y + playerSprite.getImageHeight());

        glTexCoord2f(0f, 0.0f);
        glVertex2d(pos.x, pos.y + playerSprite.getImageHeight());
        glEnd();
    }

    // Update the player's coordinates.
    @Override
    public void update()
    {
        // Create friction for the player, allow for acceleration/deceleration.
        velocity.y *= 0.97f;
        velocity.x *= 0.97f;

        // Update the player's position.
        pos.x += velocity.x;
        pos.y += velocity.y;
    }
}

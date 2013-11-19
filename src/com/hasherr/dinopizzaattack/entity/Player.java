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
    Direction faceDirection;
    Vector2 velocity;
    Texture playerSprite = TextureHandler.getTexture("dino_spritesheet", "png");

    // Player constructor.
    public Player(int x, int y)
    {
        pos = new Vector2(x, y);
        velocity = new Vector2(0.0);

    }

    private void setFaceDirection(Direction dir)
    {
        if (dir == Direction.NORTH || dir == Direction.SOUTH)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            faceDirection = dir;
        }
    }

    // Move method so that player can change positions in 2.5D.
    public void move(Direction dir)
    {

        if (dir == Direction.NORTH)
        {
            velocity.y += 60 * Game.getDeltaTime();
        }
        if (dir == Direction.SOUTH)
        {
            velocity.y += -60f * Game.getDeltaTime();
        }
        if (dir == Direction.EAST)
        {
            setFaceDirection(dir);
            velocity.x += 60f * Game.getDeltaTime();
        }
        if (dir == Direction.WEST)
        {
            setFaceDirection(dir);
            velocity.x += -60f * Game.getDeltaTime();
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
    public void draw() // Draw the player sprite onto the screen from the player's sprite sheet.
    {
        float rightOffSet = 0f;
        float leftOffSet = 0f;
        float numOfSprites = 8f;
        if (faceDirection == Direction.EAST)
        {
            rightOffSet = 2f;
            leftOffSet = 1f;
        }
        else if (faceDirection == Direction.WEST)
        {
            rightOffSet = 6f;
            leftOffSet = 5f;
        }
        playerSprite.bind();
        glBegin(GL_QUADS);
            glTexCoord2f(leftOffSet / numOfSprites, 1f);
            glVertex2d(pos.x, pos.y);

            glTexCoord2f(rightOffSet / numOfSprites, 1f);
            glVertex2d(pos.x + (playerSprite.getImageWidth() / 8), pos.y);

            glTexCoord2f(rightOffSet / numOfSprites, 0f);
            glVertex2d(pos.x + (playerSprite.getImageWidth() / 8), pos.y + (playerSprite.getImageHeight()));

            glTexCoord2f(leftOffSet / numOfSprites, 0f);
            glVertex2d(pos.x, pos.y + (playerSprite.getImageHeight()));
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

package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;
import org.newdawn.slick.opengl.Texture;
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

    public Player(int x, int y)
    {
        pos = new Vector2(x, y);
        velocity = new Vector2(0, 0);

    }

    private void debugPos()
    {
        System.out.println("X: " + pos.x + " Y: " + pos.y);
        System.out.println("Delta: " + Game.getDeltaTime() / 1000);
    }

    // Move method so that player can change positions in 2.5D.
    @Override
    public void move(Direction dir)
    {
        if (dir == Direction.NORTH)
        {
            velocity.y += 2f;
            debugPos();
        }
        else if (dir == Direction.SOUTH)
        {
            velocity.y += -2f;
            debugPos();
        }
        else if (dir == Direction.EAST)
        {
            velocity.x += 2f;
            debugPos();
        }
        else if (dir == Direction.WEST)
        {
            velocity.x += -2f;
            debugPos();
        }
    }

    @Override
    public void shoot()
    {

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
            glVertex2d(pos.x + 128, pos.y);

            glTexCoord2f(1.0f, 0.0f);
            glVertex2d(pos.x + 128, pos.y + 128);

            glTexCoord2f(0f, 0.0f);
            glVertex2d(pos.x, pos.y + 128);
        glEnd();
    }

    public void update()
    {
        velocity.y *= 0.95f;
        velocity.x *= 0.95f;

        pos.x += velocity.x;
        pos.y += velocity.y;

        draw();
    }
}

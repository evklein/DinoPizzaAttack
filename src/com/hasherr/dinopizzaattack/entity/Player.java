package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;
import javax.swing.Timer;
import org.newdawn.slick.opengl.Texture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    double moveSpeed;

    // Animation and sprite characteristics.
    float rightOffSet = 0f;
    float leftOffSet = 0f;
    float numOfSprites = 8f;

    Timer animationTimer;
    boolean eastIsSet = false;
    boolean westIsSet = false;
    boolean animationTimerHasStarted = false;

    // In-game attributes.
    int health = 100;

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

    // PLAYER ANIMATION.

    public void setOffSetAnimationBuffer()
    {
        if (faceDirection == Direction.EAST && !eastIsSet)
        {
            rightOffSet = 1f;
            leftOffSet = 2f;

            eastIsSet = true;
            westIsSet = false;
        }
        else if (faceDirection == Direction.WEST && !westIsSet)
        {
            rightOffSet = 7f;
            leftOffSet = 8f;

            eastIsSet = false;
            westIsSet = true;
        }
    }

    private void doAnimation()
    {
        setOffSetAnimationBuffer();
        animationTimer = new Timer(500, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (faceDirection == Direction.WEST)
                {
                    if (leftOffSet < 4)
                    {
                        rightOffSet++;
                        leftOffSet++;
                    }
                    else
                    {
                        rightOffSet = 0;
                        leftOffSet = 1;
                    }
                }
                else if (faceDirection == Direction.EAST)
                {
                    if (rightOffSet > 4)
                    {
                        rightOffSet--;
                        leftOffSet--;

                    }
                    else
                    {
                        rightOffSet = 7;
                        leftOffSet = 8;
                    }
                }
            }
        });

        if (!animationTimerHasStarted)
        {
            animationTimer.start();
            animationTimerHasStarted = true;
        }
    }


    // OTHER PLAYER METHODS: MOVING, COLLISION, SHOOTING.
    public void move(Direction dir)
    {
        moveSpeed = 60 * Game.getDeltaTime();
        doAnimation();

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

    private void handlePlayerCollision()
    {

    }

    // Fires a single projectile in the direction of the mouse.
    @Override
    public void shoot(Vector2 direction)
    {
        Laser projectile = new Laser(this, direction);
    }

    // DRAWING AND UPDATING THE PLAYER.

    @Override
    public Texture getSprite()
    {
         return playerSprite;
    }

    @Override
    public void draw() // Draw the player sprite onto the screen from the player's sprite sheet.
    {
        getSprite().bind();
        glBegin(GL_QUADS);
            glTexCoord2f(leftOffSet / numOfSprites, 1f);
            glVertex2d(pos.x, pos.y);

            glTexCoord2f(rightOffSet / numOfSprites, 1f);
            glVertex2d(pos.x + (getSprite().getImageWidth() / 8), pos.y);

            glTexCoord2f(rightOffSet / numOfSprites, 0f);
            glVertex2d(pos.x + (getSprite().getImageWidth() / 8), pos.y + (getSprite().getImageHeight()));

            glTexCoord2f(leftOffSet / numOfSprites, 0f);
            glVertex2d(pos.x, pos.y + (getSprite().getImageHeight()));
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

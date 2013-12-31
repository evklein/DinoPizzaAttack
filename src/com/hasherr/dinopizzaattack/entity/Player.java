package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.graphics.AnimationTool;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;
import com.hasherr.dinopizzaattack.graphics.AnimationCommand;
import javax.swing.Timer;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */
public class Player extends Entity implements Shoot
{
    Timer animationTimer;

    double lastX, lastY;

    boolean eastIsSet = false;
    boolean westIsSet = false;

    Direction faceDirection;
    Vector2 velocity;
    Texture playerSprite;
    double moveSpeed;

    // Animation and sprite characteristics.
    AnimationTool playerAnimationTool;
    float numOfSprites = 8f;

    // In-game attributes.
    int health = 100;

    // Player constructor.
    public Player(int x, int y)
    {
        pos = new Vector2(x, y);
        velocity = new Vector2(0.0);

        playerSprite = TextureHandler.getTexture("dino_spritesheet", "png");
        playerAnimationTool = new AnimationTool(numOfSprites);
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

    // OTHER PLAYER METHODS: MOVING, COLLISION, SHOOTING.
    public void move(Direction dir)
    {
        moveSpeed = 60 * Game.getDeltaTime();

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
        double stopBuffer = 0.9;
        if (velocity.x <= stopBuffer & velocity.y <= stopBuffer & velocity.x >= -stopBuffer & velocity.y >= -stopBuffer)
        {
            playerAnimationTool.doAnimation(null);
        }
        else
        {
            playerAnimationTool.doAnimation(faceDirection);
        }

        getSprite().bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2f(playerAnimationTool.getLeftOffset() / numOfSprites, 1f);
            glVertex2d(pos.x, pos.y);

            glTexCoord2f(playerAnimationTool.getRightOffset() / numOfSprites, 1f);
            glVertex2d(pos.x + (getSprite().getImageWidth() / numOfSprites), pos.y);

            glTexCoord2f(playerAnimationTool.getRightOffset() / numOfSprites, 0f);
            glVertex2d(pos.x + (getSprite().getImageWidth() / numOfSprites), pos.y + (getSprite().getImageHeight()));

            glTexCoord2f(playerAnimationTool.getLeftOffset() / numOfSprites, 0f);
            glVertex2d(pos.x, pos.y + (getSprite().getImageHeight()));
        }
        glEnd();
    }

    int counter = 1;
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

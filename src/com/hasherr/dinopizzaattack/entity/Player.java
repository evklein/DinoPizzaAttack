package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.graphics.AnimationTool;
import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;

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

    Texture playerSprite;

    // Animation and sprite characteristics.
    AnimationTool playerAnimationTool;
    float numOfSprites = 8f;

    // In-game attributes.
    int health = 100;

    // Player constructor.
    public Player(double x, double y)
    {
        super(new Vector2(x, y), new Sprite(8, TextureHandler.getTexture("dino_spritesheet2", "png")));

        playerAnimationTool = new AnimationTool(numOfSprites);
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

    // Fires a single projectile in the direction of the mouse.
    @Override
    public void shoot(Vector2 direction)
    {
        Laser projectile = new Laser(this, direction);
    }

    /*
     * Drawing, animating, and updating the player.
     */

    @Override
    public Texture getSprite()
    {
         return playerSprite;
    }

    // Draw the player.
//    @Override
//    public void draw() // Draw the player sprite onto the screen from the player's sprite sheet.
//    {
//        // Stop/start animation.
//        double stopSpeed = 0.3;
//        if (velocity.x <= stopSpeed && velocity.y <= stopSpeed && velocity.x >= -stopSpeed && velocity.y >= -stopSpeed)
//        {
//            playerAnimationTool.doAnimation(null);
//        }
//        else
//        {
//            playerAnimationTool.doAnimation(faceDirection);
//        }
//
//        sprite.draw(pos);
////        playerSprite.bind();
////        glBegin(GL_QUADS);
////        {
////            glTexCoord2f(playerAnimationTool.getLeftOffset() / numOfSprites, 1f);
////            glVertex2d(pos.x, pos.y);
////
////            glTexCoord2f(playerAnimationTool.getRightOffset() / numOfSprites, 1f);
////            glVertex2d(pos.x + (getSprite().getImageWidth() / numOfSprites), pos.y);
////
////            glTexCoord2f(playerAnimationTool.getRightOffset() / numOfSprites, 0f);
////            glVertex2d(pos.x + (getSprite().getImageWidth() / numOfSprites), pos.y + (getSprite().getImageHeight()));
////
////            glTexCoord2f(playerAnimationTool.getLeftOffset() / numOfSprites, 0f);
////            glVertex2d(pos.x, pos.y + (getSprite().getImageHeight()));
////        }
////        glEnd();
//    }

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
    }
}

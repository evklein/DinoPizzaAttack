package com.hasherr.dinopizzaattack.graphics;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.math.Vector2;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 1/6/14
 */
public class Sprite
{
    float numOfSprites;
    Texture sprite;
    //AnimationTool animationTool;

    public Sprite(float numOfSprites, Texture texture)
    {
        this.numOfSprites = numOfSprites;
        sprite = texture;
//        animationTool = new AnimationTool(numOfSprites, animationDelay);
    }

    // Alternate sprite constructor which can be used for animation.


    // Draw the sprite onto the screen.
    public void draw(Vector2 drawPos, float leftOffset, float rightOffset)
    {
//        final double STOP_SPEED = 1.0;
//
//        // Stop animating if the player stops moving.
//        if (currentVelocity.x <= STOP_SPEED && currentVelocity.y <= STOP_SPEED &&
//                currentVelocity.x >= -STOP_SPEED && currentVelocity.y >= -STOP_SPEED)
//        {
//            animationTool.doAnimation(null);
//        }
//        else
//        {
//            animationTool.doAnimation(orientation);
//        }

        sprite.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2f(leftOffset / numOfSprites, 1f);
            glVertex2d(drawPos.x, drawPos.y);

            glTexCoord2f(rightOffset / numOfSprites, 1f);
            glVertex2d(drawPos.x + (getWidth() / numOfSprites), drawPos.y);

            glTexCoord2f(rightOffset / numOfSprites, 0f);
            glVertex2d(drawPos.x + (getWidth() / numOfSprites), drawPos.y + (getHeight()));

            glTexCoord2f(leftOffset / numOfSprites, 0f);
            glVertex2d(drawPos.x, drawPos.y + (getHeight()));
        }
        glEnd();
    }

    public int getWidth() { return sprite.getImageWidth(); }
    public int getHeight() { return sprite.getImageHeight(); }
    public float getNumOfSprites() { return numOfSprites; }
    public Texture getTexture() { return sprite; }
}

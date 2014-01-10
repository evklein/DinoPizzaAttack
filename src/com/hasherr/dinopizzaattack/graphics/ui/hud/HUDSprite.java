package com.hasherr.dinopizzaattack.graphics.ui.hud;

import com.hasherr.dinopizzaattack.math.Vector2;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 1/8/14
 */
public class HUDSprite
{
    public Texture sprite;
    public HUDSprite(Texture sprite)
    {
        this.sprite = sprite;
    }

    public void draw(Vector2 drawPos, float numOfSprites, float spritePos)
    {
        sprite.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0f / 8f, 1f);
            glVertex2d(drawPos.x, drawPos.y);

            glTexCoord2f(1f / 8f, 1f);
            glVertex2d(drawPos.x + (sprite.getImageWidth() / numOfSprites), drawPos.y);

            glTexCoord2f(1f / 8f, 0f);
            glVertex2d(drawPos.x + (sprite.getImageWidth() / numOfSprites), drawPos.y + (sprite.getImageHeight()));

            glTexCoord2f(0f, 0f);
            glVertex2d(drawPos.x, drawPos.y + (sprite.getImageHeight()));
        }
        glEnd();

//        glTexCoord2f(animationTool.getLeftOffset() / numOfSprites, 1f);
//        glVertex2d(drawPos.x, drawPos.y);
//
//        glTexCoord2f(animationTool.getRightOffset() / numOfSprites, 1f);
//        glVertex2d(drawPos.x + (sprite.getImageWidth() / numOfSprites), drawPos.y);
//
//        glTexCoord2f(animationTool.getRightOffset() / numOfSprites, 0f);
//        glVertex2d(drawPos.x + (sprite.getImageWidth() / numOfSprites), drawPos.y + (sprite.getImageHeight()));
//
//        glTexCoord2f(animationTool.getLeftOffset() / numOfSprites, 0f);
//        glVertex2d(drawPos.x, drawPos.y + (sprite.getImageHeight()));
    }
}

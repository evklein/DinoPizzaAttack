package com.hasherr.dinopizzaattack.entity;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */
public class Laser extends Entity
{
    Texture laserSprite = TextureHandler.getTexture("fireball", "Png");

    // Static ArrayList for lasers so that they can be continuously drawn & updated.
    public static ArrayList<Laser> allLasers = new ArrayList<Laser>();
    public static ArrayList<Laser> deadLasers = new ArrayList<Laser>();


    // Vectors.
    Vector2 velocityVector = new Vector2(12.0); // Velocity variable to determine the speed of the laser.
    Vector2 normalizedDir; // Direction vector to handle where the laser is going.

    public Laser(Player player, Vector2 vec)
    {
        pos = new Vector2(player.pos.x + 40.0, player.pos.y + 40.0); // Set player position.
        normalizedDir = new Vector2(vec.x - (player.pos.x), vec.y - (player.pos.y)).getNormalizedVector();

        Laser.allLasers.add(this);  // Add this laser to a list of updated and rendered lasers.
    }

    @Override
    public Texture getSprite()
    {
        return laserSprite;
    }

    @Override
    public void draw()
    {
        laserSprite.bind();
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f, 1.0f);
            glVertex2d(pos.x, pos.y);

            glTexCoord2f(1.0f, 1.0f);
            glVertex2d(pos.x + laserSprite.getImageWidth(), pos.y);

            glTexCoord2f(1.0f, 0.0f);
            glVertex2d(pos.x + laserSprite.getImageWidth(), pos.y + laserSprite.getImageHeight());

            glTexCoord2f(0.0f, 0.0f);
            glVertex2d(pos.x, pos.y + laserSprite.getImageHeight());
        glEnd();
    }

    @Override public void update()
    {
        pos.x += normalizedDir.x *  (Game.getDeltaTime() * 1000);
        pos.y += normalizedDir.y * (Game.getDeltaTime() * 1000);
    }
}

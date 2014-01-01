package com.hasherr.dinopizzaattack.entity;

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

public class Raptor extends Entity
{
    Texture raptorSprite = TextureHandler.getTexture("raptor", "png");
    Vector2 raptorSpeed = new Vector2(56.0);
    Player player;

    public static ArrayList<Raptor> allRaptors = new ArrayList<Raptor>();
    public static ArrayList<Raptor> deadRaptors = new ArrayList<Raptor>();

    public Raptor(Player player)
    {
        this.player = player;
        Raptor.allRaptors.add(this);

        pos.x = (Math.random() * Game.WIDTH);
        pos.y = (Math.random() * Game.HEIGHT);
    }

    // Moves the raptor's position.
    public void move()
    {
        pos.addVector(player.pos.getNormalizedVector());
    }

    @Override
    public Texture getSprite()
    {
        return raptorSprite;
    }

    // Draws the raptor.
    @Override
    public void draw()
    {
        glBegin(GL_QUADS);
            glTexCoord2d(0.0, 1.0);
            glVertex2d(pos.x, pos.y);

            glTexCoord2d(1.0, 1.0);
            glVertex2d(pos.x + raptorSprite.getImageWidth(), pos.y);

            glTexCoord2d(1.0, 0.0);
            glVertex2d(pos.x + raptorSprite.getImageWidth(), pos.y + raptorSprite.getImageHeight());

            glTexCoord2d(0.0, 0.0);
            glTexCoord2d(pos.x, pos.y + raptorSprite.getImageHeight());
        glEnd();
    }

    // Updates the raptor.
    @Override
    public void update()
    {
        move();
    }
}

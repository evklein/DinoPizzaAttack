package com.hasherr.dinopizzaattack.entity.AI;

import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.Entity;
import com.hasherr.dinopizzaattack.entity.Player;
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
    public static ArrayList<Raptor> allRaptors = new ArrayList<Raptor>();
    public static ArrayList<Raptor> deadRaptors = new ArrayList<Raptor>();

    Vector2 raptorSpeed;
    Texture raptorSprite;
    Player player;

    public Raptor(Player player)
    {
        raptorSpeed = new Vector2(56.0);
        TextureHandler.getTexture("raptor", "png");
        this.player = player;

        Raptor.allRaptors.add(this);
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
        getSprite().bind();
        glBegin(GL_QUADS);
            glTexCoord2d(0.0, 1.0);
            glVertex2d(pos.x, pos.y);

            glTexCoord2d(1.0, 1.0);
            glVertex2d(pos.x + getSprite().getImageWidth(), pos.y);

            glTexCoord2d(1.0, 0.0);
            glVertex2d(pos.x + getSprite().getImageWidth(), pos.y + getSprite().getImageHeight());

            glTexCoord2d(0.0, 0.0);
            glTexCoord2d(pos.x, pos.y + getSprite().getImageHeight());
        glEnd();
    }

    // Updates the raptor.
    @Override
    public void update()
    {
        move();
    }
}

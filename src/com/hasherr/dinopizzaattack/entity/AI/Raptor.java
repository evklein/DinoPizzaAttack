package com.hasherr.dinopizzaattack.entity.ai;

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

    Player player;
    Vector2 direction;
    Texture raptorSprite;

    public Raptor(Vector2 startingPos, Player player)
    {
        pos = startingPos;
        this.player = player;
        raptorSprite = TextureHandler.getTexture("raptor", "Png");

        Raptor.allRaptors.add(this);
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
        raptorSprite.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0.0, 1.0);
            glVertex2d(pos.x, pos.y);

            glTexCoord2d(1.0, 1.0);
            glVertex2d(pos.x + raptorSprite.getImageWidth(), pos.y);

            glTexCoord2d(1.0, 0.0);
            glVertex2d(pos.x + raptorSprite.getImageWidth(), pos.y + raptorSprite.getImageHeight());

            glTexCoord2d(0.0, 0.0);
            glVertex2d(pos.x, pos.y + raptorSprite.getImageHeight());
        }
        glEnd();
    }

    // Updates the raptor.
    @Override
    public void update()
    {
        direction = new Vector2(pos.x - (player.pos.x), pos.y - (player.pos.y)).getNormalizedVector();

        pos.x += direction.x + (Game.getDeltaTime() * 100);
        pos.y += direction.y + (Game.getDeltaTime() * 100);
    }
}

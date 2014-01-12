package com.hasherr.dinopizzaattack.screens;

import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.ai.AIGenerator;
import com.hasherr.dinopizzaattack.entity.projectile.Laser;
import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.entity.ai.Raptor;
import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.graphics.ui.hud.HUDSprite;
import com.hasherr.dinopizzaattack.math.Vector2;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public class GameScreen extends Screen
{
    Player player; // Create a new player object.

    AIGenerator aiGenerator;
    Random aiRandom;
    boolean aiSwitcher;
    boolean lastSwitcherValue;
    Timer aiDeploymentTimer;
    boolean timerHasStarted;

    Texture background;

    public GameScreen()
    {
        player = new Player(300, 200);

        aiGenerator = new AIGenerator();
        aiRandom = new Random();
        aiSwitcher = false;
        lastSwitcherValue = true;
        timerHasStarted = false;

        background = TextureHandler.getTexture("background", "Png");
    }


    private void doTimer()
    {
        aiDeploymentTimer = new Timer(800, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (aiDeploymentTimer.isRepeats())
                {
                    if (aiSwitcher)
                        aiSwitcher = false;
                    else
                        aiSwitcher = true;
                }
            }
        });

        if (!timerHasStarted)
        {
            timerHasStarted = true;
            aiDeploymentTimer.start();
        }
    }

    @Override
    public void render()
    {
        // Draw the background.
        float u = ((float) Game.WIDTH) / background.getTextureWidth();
        float v = ((float) Game.HEIGHT) / background.getTextureHeight();
        background.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0f, v);
            glVertex2d(0f, 0f);

            glTexCoord2f(u, v);
            glVertex2d(Game.WIDTH, 0);

            glTexCoord2f(u, 0f);
            glVertex2d(Game.WIDTH, Game.HEIGHT);

            glTexCoord2f(0f, 0f);
            glVertex2d(0, Game.HEIGHT);
        }
        glEnd();

        HUDSprite playerHealth = new HUDSprite(TextureHandler.getTexture("health_spritesheet", "Png"));
        playerHealth.draw(new Vector2(20, Game.HEIGHT - playerHealth.sprite.getImageHeight()), 8f, 1f);

        for (Laser laser : Laser.allLasers)
        {
            laser.draw(8f, laser.getLeftOffset(), laser.getRightOffset());
        }

        for (Raptor raptor : Raptor.allRaptors)
        {
            raptor.draw(8f, raptor.getLeftOffset(), raptor.getRightOffset());
        }

        player.draw(8f, player.getLeftOffset(), player.getRightOffset());
    }

    @Override
    public void update()
    {
        doTimer();

        if (aiSwitcher == lastSwitcherValue)
        {
            lastSwitcherValue = !aiSwitcher;
            aiGenerator.generateNewEntity(player);
        }

        for (Laser laser : Laser.allLasers)
        {
            laser.update();

            // GC the lasers if they go out of bounds.
            if (laser.pos.x > Game.WIDTH || laser.pos.x < 0 ||
                laser.pos.y > Game.HEIGHT || laser.pos.y < 0)
            {
            Laser.deadLasers.add(laser);
        }
        }

        for (Laser laser : Laser.deadLasers)
        {
            Laser.allLasers.remove(laser);
        }

        Laser.deadLasers.clear();


        for (Raptor raptor : Raptor.allRaptors)
        {
            if (raptor.getIsAlive())
            {
                raptor.update();
            }
            // GC the raptors if they go out of bounds.
            if (raptor.pos.x > Game.WIDTH + 2000 || raptor.pos.y < -2000 || !raptor.getIsAlive())
            {
                raptor.setIsAlive(false);
                Raptor.deadRaptors.add(raptor);
            }
        }

        for (Raptor raptor : Raptor.deadRaptors)
        {
            Raptor.allRaptors.remove(raptor);
        }
        Raptor.deadRaptors.clear();

        player.update();
    }

    // Return current in-use player object.
    public Player getPlayer()
    {
        return player;
    }
}

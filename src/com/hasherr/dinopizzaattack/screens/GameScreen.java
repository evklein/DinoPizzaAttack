package com.hasherr.dinopizzaattack.screens;

import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.ai.AIGenerator;
import com.hasherr.dinopizzaattack.entity.Laser;
import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.entity.ai.Raptor;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

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
    Timer aiTimer;
    boolean aiIsRunning;
    Texture background;

    public GameScreen()
    {
        player = new Player(300, 200);

        aiRandom = new Random();
        aiGenerator = new AIGenerator();
        aiIsRunning = false;
        background = TextureHandler.getTexture("background", "png");


        // Create a timer which sets off randomly between 20 ms and 350 ms and sets off an enemy.
        final int MIN_TIME = 20;
        final int MAX_TIME = 350;
//        aiTimer = new Timer(aiRandom.nextInt(MAX_TIME) + MIN_TIME, new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                aiGenerator.generateNewEntity(player);
//            }
//        });
    }

    @Override
    public void render()
    {
        for (Laser laser : Laser.allLasers)
        {
            laser.draw();
        }

        for (Raptor raptor : Raptor.allRaptors)
        {
            raptor.draw();
        }

        player.draw();
    }

    @Override
    public void update()
    {
        // Start the AI if it hasn't started already.
//        if (!aiIsRunning)
//        {
//            aiIsRunning = true;
//            aiTimer.start();
//        }

        if (Keyboard.isKeyDown(Keyboard.KEY_O))
        {
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
            raptor.update();

            // GC the raptors if they go out of bounds.
            if (raptor.pos.x > Game.WIDTH + 2000 || raptor.pos.y < -2000)
            {
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

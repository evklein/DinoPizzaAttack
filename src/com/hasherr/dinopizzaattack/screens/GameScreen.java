package com.hasherr.dinopizzaattack.screens;

import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.ai.AIGenerator;
import com.hasherr.dinopizzaattack.entity.projectile.Laser;
import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.entity.ai.Raptor;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import javax.swing.*;
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

    Texture background;

    public GameScreen()
    {
        player = new Player(300, 200);

        aiRandom = new Random();
        aiGenerator = new AIGenerator();
    }

    @Override
    public void render()
    {
        for (Laser laser : Laser.allLasers)
        {
            laser.draw(8f);
        }

        for (Raptor raptor : Raptor.allRaptors)
        {
            raptor.draw(8f);
        }

        player.draw(8f);
    }

    @Override
    public void update()
    {
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

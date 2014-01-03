package com.hasherr.dinopizzaattack.screens;

import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.ai.AIGenerator;
import com.hasherr.dinopizzaattack.entity.Laser;
import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.entity.ai.Raptor;
import com.hasherr.dinopizzaattack.math.Vector2;
import org.lwjgl.input.Keyboard;

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

    Timer aiTimer = new Timer(new Random().nextInt(1000), new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            aiGenerator.generateNewEntity(player);
        }
    });

    public GameScreen()
    {
        player = new Player(300, 200);
        aiGenerator = new AIGenerator();



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
        if (Keyboard.isKeyDown(Keyboard.KEY_O))
        {
            Raptor r = new Raptor(new Vector2(50, 100), player);
            Raptor a = new Raptor(new Vector2(50, 125), player);
            Raptor p = new Raptor(new Vector2(100, 100), player);
            Raptor t = new Raptor(new Vector2(150, 100), player);
            Raptor o = new Raptor(new Vector2(150, 150), player);
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
            if (raptor.pos.x > Game.WIDTH + 1000 || raptor.pos.y < -1000)
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

    private void createRaptors()
    {

    }

    // Return current in-use player object.
    public Player getPlayer()
    {
        return player;
    }

}

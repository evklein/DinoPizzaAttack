package com.hasherr.dinopizzaattack.screens;

import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.Laser;
import com.hasherr.dinopizzaattack.entity.Player;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public class GameScreen extends Screen
{

    Player player = new Player(0, 0); // new Player(0, 0); // Instantiate a new player.

    @Override
    public void render()
    {
        for (Laser laser : Laser.allLasers)
        {
            laser.draw();

            if (laser.pos.x > Game.width || laser.pos.x < 0 || laser.pos.y > Game.height || laser.pos.y < 0)
            {
                Laser.deadLasers.add(laser);
            }

        }

        for (Laser laser : Laser.deadLasers)
        {
            Laser.allLasers.remove(laser);
        }

        Laser.deadLasers.clear();

        player.draw();
    }

    @Override
    public void update()
    {
        for (Laser laser : Laser.allLasers)
        {
            laser.update();

            if (laser.pos.x > Game.width || laser.pos.x < 0 || laser.pos.y > Game.height || laser.pos.y < 0)
            {
                Laser.deadLasers.add(laser);
            }

        }

        for (Laser laser : Laser.deadLasers)
        {
            Laser.allLasers.remove(laser);
        }

        Laser.deadLasers.clear();

        player.update();
    }

}

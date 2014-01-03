package com.hasherr.dinopizzaattack.screens;

import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.ai.AIGenerator;
import com.hasherr.dinopizzaattack.entity.Laser;
import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.entity.ai.Raptor;
import com.hasherr.dinopizzaattack.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public class GameScreen extends Screen
{
    Player player; // Create a new player object.
    AIGenerator aiGenerator;

    public GameScreen()
    {
        player = new Player(0.0, 0.0);
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
        aiGenerator.generateNewEntity(player);

        for (Laser laser : Laser.allLasers)
        {
            laser.update();

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

package com.hasherr.dinopizzaattack.screens;

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
    }

    @Override
    public void update()
    {
        player.update();
    }

}

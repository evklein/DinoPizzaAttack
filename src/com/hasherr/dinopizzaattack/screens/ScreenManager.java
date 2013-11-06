package com.hasherr.dinopizzaattack.screens;

import com.hasherr.dinopizzaattack.entity.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public class ScreenManager
{
    ArrayList<Screen> runningScreens = new ArrayList<Screen>();

    public ScreenManager()
    {
        runningScreens.add(new GameScreen());
    }

    private Screen getLast()
    {
        return runningScreens.get(runningScreens.size() - 1);
    }

    public void push()
    {

    }

    public void pop()
    {

    }

    // Renders the screen at a continuous rate.
    public void render()
    {
       getLast().render(); // .get(runningScreens.size() - 1).render();
    }

    // Updates the screen (60 FPS).
    public void update()
    {
        getLast().update();
    }

    public Player getPlayer()
    {
        Player playerToReturn;

        if (getLast() instanceof GameScreen)
        {
            playerToReturn = ((GameScreen) getLast()).player;
        } else
        {
            playerToReturn = null;
        }

        return  playerToReturn;
    }
}

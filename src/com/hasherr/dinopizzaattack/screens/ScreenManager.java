package com.hasherr.dinopizzaattack.screens;

import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public class ScreenManager
{
    /* Treat the screens like a stack, using pop() and push() methods
       to control the user's experience between multiple screens.
     */
    ArrayList<Screen> runningScreens = new ArrayList<Screen>();

    // Temporary constructor.
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
       getLast().render();
    }

    // Updates the screen every frame.
    public void update()
    {
        getLast().update();
    }

    // Return the player if needed.
    public Player getPlayer()
    {
        Player playerToReturn;

        if (getLast() instanceof GameScreen)
        {
            playerToReturn = ((GameScreen) getLast()).player;
        }
        else
        {
            playerToReturn = null;
        }

        return  playerToReturn;
    }
}

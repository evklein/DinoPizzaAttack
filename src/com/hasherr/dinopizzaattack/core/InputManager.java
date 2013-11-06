package com.hasherr.dinopizzaattack.core;

import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.screens.ScreenManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */
public class InputManager
{
    ScreenManager screenManager;

    public InputManager(ScreenManager screenManager)
    {
        this.screenManager = screenManager;
    }

    // Handle all user input.
    public void handleInput(Player player)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            player.move(Direction.NORTH);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            player.move(Direction.SOUTH);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            player.move(Direction.EAST);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            player.move(Direction.WEST);
        }

        if (Mouse.isButtonDown(0))
        {
            System.out.println("X: " + Mouse.getX() + " Y: " + Mouse.getY());
        }
    }
}

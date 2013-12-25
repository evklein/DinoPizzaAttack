package com.hasherr.dinopizzaattack.core;

import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.math.Vector2;
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

    Vector2 mousePosition = new Vector2(0, 0);
    boolean isMouseButtonDown = false;

    // Handle all user input.
    public void pollInput(Player player)
    {

        if (Mouse.next()) // Recalculate the coordinates of the mouse every frame for shooting purposes.
        {
            mousePosition.setX(Mouse.getX());
            mousePosition.setY(Mouse.getY());
        }

        // Movement via keyboard.
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

        if (Keyboard.isKeyDown(Keyboard.KEY_O))
        {
        }

        // Shooting controls, player can only shoot one bullet per click.
        if (Mouse.isButtonDown(0)) // If left mouse click.
        {
            if (!isMouseButtonDown)
            {
                isMouseButtonDown = true;
                player.shoot(mousePosition);
            }
        }
        else
        {
            isMouseButtonDown = false;
        }

    }
}

package com.hasherr.dinopizzaattack.core;

import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.math.Vector2;
import com.hasherr.dinopizzaattack.screens.GameScreen;
import com.hasherr.dinopizzaattack.screens.PauseScreen;
import com.hasherr.dinopizzaattack.screens.Screen;
import com.hasherr.dinopizzaattack.screens.TitleScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */
public class InputManager
{
    Vector2 mousePosition;
    boolean isMouseButtonDown;

    public InputManager()
    {
        mousePosition = new Vector2(0.0);
        isMouseButtonDown = false;
    }

    // Handle all user input.
    public void pollInput(Screen currentScreen, Player player)
    {
        // Handle the input while the game is running in it's main state (main game loop).
        if (currentScreen instanceof GameScreen)
        {
            // Player movement via keyboard control.
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

            // Recalculate the coordinates of the mouse every frame for shooting purposes.
            if (Mouse.next())
            {
                mousePosition.setX(Mouse.getX());
                mousePosition.setY(Mouse.getY());
            }

            // Shooting controls, player can only shoot one bullet per click.
            if (Mouse.isButtonDown(0)) // Left mouse click.
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
        // Handle the user's input if the user currently exists in the game's title screen.
        else if (currentScreen instanceof TitleScreen)
        {

        }
        // Handle the user's input if the user is in the pause screen.
        else if (currentScreen instanceof PauseScreen)
        {

        }
    }
}

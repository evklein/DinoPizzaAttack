package com.hasherr.dinopizzaattack.core;

import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.screens.ScreenManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public class Game
{
    ScreenManager screenManager;
    InputManager inputManager;

    static float time, fps, lastFps, lastFrame, delta;

    public static void main(String[] args) // Main method for running.
    {
        new Game().initGL(1000, 700); // Start the game.
    }

    private void initGL(int width, int height) // Displaying screen using OpenGL.
    {

        try
        {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        lastFps = getTime();

        // Intitial GL elements.
        glMatrixMode(GL_PROJECTION);                // *
        glLoadIdentity();                           // *
        glOrtho(0, width, 0, height, 1, -1);        // *
        glMatrixMode(GL_MODELVIEW);                 // *
        glEnable(GL_TEXTURE_2D);                    // *

        screenManager = new ScreenManager();
        inputManager = new InputManager(screenManager);

        // Clear the screen for efficient redering of sprites and textures.



        // Infinite game logic loop.
        while (!Display.isCloseRequested())
        {
            time = getTime();
            delta = time - lastFrame;
            lastFrame = time;


            Display.update();
            glClear(GL_COLOR_BUFFER_BIT);

            displayDelta();
            Display.sync(60); // Cap the FPS to 60 frames.

            inputManager.handleInput(screenManager.getPlayer()); // Handle all player input for the game.

            screenManager.update();
            screenManager.render();

        }

        Display.destroy(); // If the Display is requested, exit the main while loop and destroy the display (Exit).
    }

    /* Timer elements and Delta. */

    // Return the system time in milliseconds.
    private static float getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public static float getDeltaTime()
    {
        return delta;
    }

    private void displayDelta()
    {
        if (getTime() - lastFps > 1000)
        {
            Display.setTitle("Dino Pizza Attack! FPS: " + fps);
            fps = 0;
            lastFps += 1000;
        }
        fps++;
    }

}


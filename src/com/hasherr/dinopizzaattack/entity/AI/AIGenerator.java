package com.hasherr.dinopizzaattack.entity.AI;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.math.Vector2;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 1/1/14
 */
public class AIGenerator
{
    Random generator;

    Direction side;

    public AIGenerator()
    {
        generator = new Random();
    }

    private int getRand(int minimum, int maximum)
    {
        Random generator = new Random();

        return generator.nextInt((maximum - minimum) + 1) + minimum;
    }

    // Returns the initial position of a raptor for when it starts.
    public Vector2 generateInitialCoordinates()
    {
        Vector2 startingPos;
        int sideSignature = generator.nextInt(3);

        if (sideSignature == 0)
        {
            // Set coordinates for the top (north) side.
            startingPos = new Vector2(getRand(0, Game.WIDTH), getRand(Game.HEIGHT, Game.HEIGHT + 300));
        }
        else if (sideSignature == 1)
        {
            // Set coordinates for the bottom (south) side.
            startingPos = new Vector2(getRand(0, Game.WIDTH), getRand(-Game.HEIGHT, -Game.HEIGHT + 300));
        }
        else if (sideSignature == 2)
        {
            // Set coordinates for the right (east) side.
           startingPos = new Vector2(getRand(Game.WIDTH, Game.WIDTH + 300), getRand(0, Game.HEIGHT));
        }
        else
        {
            // Set the coordinates for the left (west) side.
            startingPos = new Vector2(getRand(-300, 0), getRand(0, Game.HEIGHT));
        }

        return startingPos;
    }
//
//    public Vector2 getDirectionVector()
//    {
//
//    }
}
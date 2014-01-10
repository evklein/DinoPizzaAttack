package com.hasherr.dinopizzaattack.graphics;

import com.hasherr.dinopizzaattack.core.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 12/27/13
 */
public class AnimationTool
{
    Direction orientation;
    Direction currentOrientation;

    final int TIMER_DELAY = 100;
    float numOfSprites;
    float leftOffset;
    float rightOffset;

    boolean eastIsSet;
    boolean westIsSet;
    boolean clockHasStarted;

    Timer animationTimer;

    public AnimationTool(float numOfSprites)
    {
        this.numOfSprites = numOfSprites;

        clockHasStarted = false;
        rightOffset = 0f;
        leftOffset = 1f;
        eastIsSet = false;
        westIsSet = false;
    }

    // Offset the animation image so that sprites using a spritesheet and separate their image.
    private void setAnimationOffset(Direction orientation)
    {
        this.orientation = orientation;

        if (orientation == Direction.EAST && !eastIsSet)
        {
            rightOffset = 0f;
            leftOffset = 1f;

            eastIsSet = true;
            westIsSet = false;
        }
        else if (orientation == Direction.WEST && !westIsSet)
        {
            rightOffset = numOfSprites - 1;
            leftOffset = numOfSprites;

            eastIsSet = false;
            westIsSet = true;
        }
    }

    public void doAnimation(Direction orientation)
    {
        currentOrientation = orientation;
        setAnimationOffset(currentOrientation);

        animationTimer = new Timer(TIMER_DELAY, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (currentOrientation == Direction.WEST)
                {
                    if (leftOffset < (numOfSprites / 2))
                    {
                        rightOffset++;
                        leftOffset++;
                    }
                    else
                    {
                        rightOffset = 0;
                        leftOffset = 1;
                    }
                }
                else if (currentOrientation == Direction.EAST)
                {
                    if (rightOffset > (numOfSprites / 2))
                    {
                        rightOffset--;
                        leftOffset--;
                    }
                    else
                    {
                        rightOffset = numOfSprites - 1;
                        leftOffset = numOfSprites;
                    }
                }
            }
        });

        // Start the timer for the first time.
        if (orientation == null)
        {
            animationTimer.stop();
        }
        if (!clockHasStarted)
        {
            clockHasStarted = true;
            animationTimer.start();
        }
    }

    // An alternate method of animating which doesn't require an orientation.
    public void doAnimation()
    {
        animationTimer = new Timer(TIMER_DELAY, new ActionListener()
        {
            int counter = 0;
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Count: " + counter);
                counter++;
                if (counter == 8) counter = 0;

                rightOffset = counter;
                leftOffset = counter + 1;
            }
        });

        if (!clockHasStarted)
        {
            clockHasStarted = true;
            animationTimer.start();
        }
    }

    public float getLeftOffset()  { return leftOffset;  }
    public float getRightOffset() { return rightOffset; }
}

package com.hasherr.dinopizzaattack.graphics;

import com.hasherr.dinopizzaattack.core.Direction;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 12/27/13
 */
public class AnimationTool
{
    Direction dir;
    Direction faceDirection;
    float numOfSprites;

    final int TIMER_INTERVAL = 100;
    float leftOffset = 0f;
    float rightOffset = 0f;

    boolean eastIsSet = false;
    boolean westIsSet = false;
    boolean clockHasStarted = false;

    Timer animationTimer;

    public AnimationTool(float numOfSprites)
    {
        this.numOfSprites = numOfSprites;
    }

    private void setAnimationOffset(Direction faceDirection)
    {
        this.faceDirection = faceDirection;

        if (faceDirection == Direction.EAST && !eastIsSet)
        {
            rightOffset = 0f;
            leftOffset = 1f;

            eastIsSet = true;
            westIsSet = false;
        }
        else if (faceDirection == Direction.WEST && !westIsSet)
        {
            rightOffset = numOfSprites - 1;
            leftOffset = numOfSprites;

            eastIsSet = false;
            westIsSet = true;
        }
    }

    public void doAnimation(Direction dire)
    {
        setAnimationOffset(dire);

        animationTimer = new Timer(TIMER_INTERVAL, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (faceDirection == Direction.WEST)
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
                else if (faceDirection == Direction.EAST)
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

        if (!clockHasStarted)
        {
            animationTimer.start();
            clockHasStarted = true;
        }
    }

    public float getLeftOffset() { return leftOffset; }
    public float getRightOffset() { return rightOffset; }
}

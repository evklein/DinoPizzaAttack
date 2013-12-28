package com.hasherr.dinopizzaattack.graphics;

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
    Timer animationTimer;

    int numOfSprites;
    final int TIMER_INTERVAL = 250;

    public AnimationTool(int numOfSprites)
    {
        this.numOfSprites = numOfSprites;

        animationTimer = new Timer(TIMER_INTERVAL, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
    }

    public void setAnimationOffset()
    {

    }

    public void doAnimation()
    {
        setAnimationOffset();
    }
}

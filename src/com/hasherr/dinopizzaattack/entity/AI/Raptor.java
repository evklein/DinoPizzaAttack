package com.hasherr.dinopizzaattack.entity.ai;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.Entity;
import com.hasherr.dinopizzaattack.entity.Movable;
import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.graphics.AnimationTool;
import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;
import javafx.animation.Animation;
import org.newdawn.slick.opengl.Texture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */

public class Raptor extends Entity implements Movable
{
    public static ArrayList<Raptor> allRaptors = new ArrayList<Raptor>();
    public static ArrayList<Raptor> deadRaptors = new ArrayList<Raptor>();

    Player player;
    Vector2 direction;
    AnimationTool animationTool;
    Timer deploymentTimer;
    boolean timerHasStarted;
    boolean isAlive;

    public Raptor(Vector2 startingPos, Player player)
    {
        super(startingPos, new Sprite(8f, TextureHandler.getTexture("raptor_spritesheet", "Png")),
                Direction.WEST);
        Raptor.allRaptors.add(this);

        animationTool = new AnimationTool(8f);
        this.player = player;
        pos = startingPos;
        direction = new Vector2(player.pos.x - (pos.x), player.pos.y - (pos.y)).getNormalizedVector();
        timerHasStarted = false;
        isAlive = true;
    }

    // Recalculate the path every 1.2 seconds so that the AI seems 'smarter.'
    private void recalculatePath()
    {
        deploymentTimer = new Timer(1200, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                direction = new Vector2(player.pos.x - (pos.x), player.pos.y - (pos.y)).getNormalizedVector();
                setOrientation(pos.x < player.pos.x ? Direction.EAST : Direction.WEST);
            }
        });

        if (!timerHasStarted)
        {
            timerHasStarted = true;
            deploymentTimer.start();
        }
    }

    @Override
    public void animate()
    {
        animationTool.doAnimation(getOrientation());
    }

    // Updates the raptor.
    @Override
    public void update()
    {
        recalculatePath();
        animate();

        final int MAX_SPEED = 7;
        pos.x += direction.x * (Game.getDeltaTime() * 100) * MAX_SPEED;
        pos.y += direction.y * (Game.getDeltaTime() * 100) * MAX_SPEED;

        updateBoundingBox();

        if (getBoundingBox().collidesWith(player.getBoundingBox()))
        {
            player.takeDamage();

            deploymentTimer.stop();
            direction = new Vector2((pos.x) - player.pos.x, (pos.y) - player.pos.y).getNormalizedVector();
        }
    }

    @Override public float getLeftOffset() { return animationTool.getLeftOffset(); }
    @Override public float getRightOffset() { return animationTool.getRightOffset(); }
    public boolean getIsAlive() { return isAlive; }
    public void setIsAlive(boolean isAlive) { this.isAlive = isAlive; }
}

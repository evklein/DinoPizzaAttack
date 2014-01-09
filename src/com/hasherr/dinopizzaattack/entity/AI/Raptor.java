package com.hasherr.dinopizzaattack.entity.ai;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.Entity;
import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */

public class Raptor extends Entity
{
    public static ArrayList<Raptor> allRaptors = new ArrayList<Raptor>();
    public static ArrayList<Raptor> deadRaptors = new ArrayList<Raptor>();

    Player player;
    Vector2 direction;

    public Raptor(Vector2 startingPos, Player player)
    {
        super(startingPos, new Sprite(8f, TextureHandler.getTexture("raptor_spritesheet", "Png")), Direction.EAST);
        Raptor.allRaptors.add(this);

        this.player = player;
        direction = new Vector2(player.pos.x - (startingPos.x), player.pos.y - (startingPos.y)).getNormalizedVector();
    }

    // Updates the raptor.
    @Override
    public void update()
    {
        final int MAX_SPEED = 7;
        pos.x += direction.x * (Game.getDeltaTime() * 100) * MAX_SPEED;
        pos.y += direction.y * (Game.getDeltaTime() * 100) * MAX_SPEED;
    }
}

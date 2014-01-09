package com.hasherr.dinopizzaattack.entity.projectile;

import com.hasherr.dinopizzaattack.core.Direction;
import com.hasherr.dinopizzaattack.core.Game;
import com.hasherr.dinopizzaattack.entity.Entity;
import com.hasherr.dinopizzaattack.entity.Player;
import com.hasherr.dinopizzaattack.graphics.Sprite;
import com.hasherr.dinopizzaattack.graphics.TextureHandler;
import com.hasherr.dinopizzaattack.math.Vector2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 11/2/13
 */
public class Laser extends Entity
{
    // Static ArrayList for lasers so that they can be continuously drawn & updated.
    public static ArrayList<Laser> allLasers = new ArrayList<Laser>();
    public static ArrayList<Laser> deadLasers = new ArrayList<Laser>();

    Vector2 direction; // Direction vector to handle where the laser is going.

    // Laser constructor.
    public Laser(Player player, Vector2 mouse)
    {
        super(new Vector2(player.pos.x + (32.0), player.pos.y + (16.0)),
                new Sprite(8f, TextureHandler.getTexture("particle_spritesheet", "Png")),
                Direction.EAST);
        Laser.allLasers.add(this);  // Add this laser to a list of updated and rendered lasers.

        direction = new Vector2(mouse.x - (player.pos.x), mouse.y - (player.pos.y)).getNormalizedVector();
    }

    // Update the laser's position.
    @Override
    public void update()
    {
        pos.x += direction.x * (Game.getDeltaTime() * 1000);
        pos.y += direction.y * (Game.getDeltaTime() * 1000);
    }
}

package com.alexyuan.Entity.Cretures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.Entity.Entity;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.Camera;

public abstract class Enemy extends Creture {

    protected AABB sense;
    protected int r_sense;

    protected AABB attackrange;
    protected int r_attackrange;

    private Camera cam;

    protected int xOffset;
    protected int yOffset;

    protected ArrayList<Entity> collisions;

    public Enemy(Camera cam, SpriteSheet sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        this.cam = cam;

        bounds.setWidth(size / 2);
        bounds.setHeight(size / 2 - yOffset);
        bounds.setXOffset(size / 2 - xOffset);
        bounds.setYOffset(size / 2 + yOffset);

        sense = new AABB(new Vector2f(origin.getX() + size / 2 - r_sense / 2, origin.getY() + size / 2 - r_sense / 2), r_sense);
        attackrange = new AABB(new Vector2f(origin.getX() + bounds.getXOffset() + bounds.getWidth() / 2 - r_attackrange / 2 , origin.getY() + bounds.getYOffset() + bounds.getHeight() / 2 - r_attackrange / 2 ), r_attackrange);
    }

    public void chase(Player player) {
        AABB playerBounds = player.getBounds();
        if (sense.colCircleBox(playerBounds) && !attackrange.colCircleBox(playerBounds)) {
            if (pos.getY() > player.getPos().getY() + 1) {
                up = true;
            } else {
                up = false;
            }
            if (pos.getY() < player.getPos().getY() - 1) {
                down = true;
            } else {
                down = false;
            }

            if (pos.getX() > player.getPos().getX() + 1) {
                left = true;
            } else {
                left = false;
            } 
            if (pos.getX() < player.getPos().getX() - 1) {
                right = true;
            } else {
                right = false;
            }
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
        }
    }

    public void update(Player player, double time) {
        if(cam.getBounds().collides(this.bounds)) {
            super.update(time);
            chase(player);
            move();

            if(teleported) {
                teleported = false;
                
            bounds.setWidth(size / 2);
            bounds.setHeight(size / 2 - yOffset);
            bounds.setXOffset(size / 2 - xOffset);
            bounds.setYOffset(size / 2 + yOffset);

            hitBounds = new AABB(pos, size, size);
            hitBounds.setXOffset(size / 2);

            sense = new AABB(new Vector2f(pos.getX() + size / 2 - r_sense / 2, pos.getY() + size / 2 - r_sense / 2), r_sense);
            attackrange = new AABB(new Vector2f(pos.getX() + bounds.getXOffset() + bounds.getWidth() / 2 - r_attackrange / 2 , pos.getY() + bounds.getYOffset() + bounds.getHeight() / 2 - r_attackrange / 2 ), r_attackrange);
            }

            if(attackrange.colCircleBox(player.getBounds()) && !isInvincible) {
                attack = true;
                player.setHealth(player.getHealth() - damage, 5f * getDirection(), currentDirection == UP || currentDirection == DOWN);
            } else {
                attack = false;
            }

            if (!fallen) {
                if (!tc.collisionTile(dx, 0)) {
                    sense.getPos().addX(dx);
                    attackrange.getPos().addX(dx);
                    pos.addX(dx);
                }
                if (!tc.collisionTile(0, dy)) {
                    sense.getPos().addY(dy);
                    attackrange.getPos().addY(dy);
                    pos.addY(dy);
                }
            } else {
                if(ani.hasPlayedOnce()) {
                    die = true;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        if(cam.getBounds().collides(this.bounds)) { 

            //if(isInvincible) 
            if(useRight && left) {
                g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()) + size, (int) (pos.getWorldVar().getY()), -size, size, null);
            } else {
                g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
            }
            
			
            // Health Bar UI
            g.setColor(Color.red);
			g.fillRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() - 5), 24, 5);
			
			g.setColor(Color.green);
            g.fillRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() - 5), (int) (24 * healthPercent), 5);
            
        }
    }
}
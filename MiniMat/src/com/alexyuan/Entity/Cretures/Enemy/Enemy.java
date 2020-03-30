package com.alexyuan.Entity.Cretures.Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.Entity.Entity;
import com.alexyuan.Entity.Cretures.Creture;
import com.alexyuan.Entity.Cretures.Player;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.Camera;

public abstract class Enemy extends Creture {

    private Camera cam;

    protected int xOffset;
    protected int yOffset;

    protected ArrayList<Entity> collisions;

    public Enemy(Camera cam, SpriteSheet sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        this.cam = cam;            
    }

    public void chase(Player player) {

        if (!hitBounds.collides(player.getBounds())) {
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
            attacking = isAttacking(time);
            
            chase(player);
            move();

            if(hitBounds.collides(player.getBounds()) && !isInvincible) {
                attack = true;
                player.setHealth(player.getHealth() - damage, 5f * getDirection(), currentDirection == UP || currentDirection == DOWN);
            } else {
                attack = false;
            }

            if (!fallen) {
                if (!tc.collisionTile(dx, 0)) {
                    hitBounds.getPos().addX(dx);
                    pos.addX(dx);
                }
                if (!tc.collisionTile(0, dy)) {
                    hitBounds.getPos().addY(dy);
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
    	
        if(cam.getBounds().collides(bounds)) { 
        	
            //if(isInvincible) 
            if(useRight && left) {
                g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()) + size, (int) (pos.getWorldVar().getY()), -size, size, null);
            } else {
                g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
            }
			
            // Health Bar UI
            g.setColor(Color.red);
			g.fillRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().getY() - 5), 50, 5);
			g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
			if(attack)
				g.drawRect((int) (hitBounds.getPos().getWorldVar().getX() + hitBounds.getXOffset()), (int) (hitBounds.getPos().getWorldVar().getY() + hitBounds.getYOffset()), (int) hitBounds.getWidth(), (int) hitBounds.getHeight());
			
			g.setColor(Color.green);
            g.fillRect((int) (pos.getWorldVar().getX() + 7), (int) (pos.getWorldVar().getY() - 5), (int) (50 * healthPercent), 5);

        }
        
    }
}
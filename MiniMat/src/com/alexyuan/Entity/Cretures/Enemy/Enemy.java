package com.alexyuan.Entity.Cretures.Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.Entity.Entity;
import com.alexyuan.Entity.Cretures.Creture;
import com.alexyuan.Entity.Cretures.Player.Player;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.Camera;

public abstract class Enemy extends Creture {

    private Camera cam;

    protected AABB attackrange;
    protected int r_attackrange;

    protected ArrayList<Entity> collisions;

    public Enemy(Camera cam, SpriteSheet sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        this.cam = cam;            
    }

    public void chase(Player player) {

        if (!attackrange.colCircleBox(player.getBounds()) && sense.colCircleBox(player.getBounds())) {
            if (pos.getY() + bounds.getHeight() > player.getBounds().getHeight() + player.getPos().getY() + 5) {
                up = true;
            } else {
                up = false;
            }
            if (pos.getY() + bounds.getHeight() < player.getBounds().getHeight() + player.getPos().getY() - 5) {
                down = true;
            } else {
                down = false;
            }

            if (pos.getX() + bounds.getWidth() > player.getBounds().getWidth() + player.getPos().getX() + 5) {
                left = true;
            } else {
                left = false;
            } 
            if (pos.getX() + bounds.getWidth() < player.getBounds().getWidth() + player.getPos().getX() - 5) {
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
            this.animate();
            
            if (!fallen) {

	            attacking = isAttacking(time);
	            
		       	move();
		        chase(player);
	            
	            if(attackrange.colCircleBox(player.getBounds()) && !isInvincible) {
	        	
	                attackTime = System.nanoTime();
	                if(attacking) {
	                	player.setHealth(player.getHealth() - damage, force * getDirection(), currentDirection == UP || currentDirection == DOWN);
	                }
	            }

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
            	left = false;
            	right = false;
            	up = false;
            	down = false;
            	
            	if(ani.hasPlayed(1))
            		die = true;
            }
                 
        }
    }

    @Override
    public void render(Graphics2D g) {

    	g.setColor(Color.blue);
    	g.drawOval((int) sense.getPos().getWorldVar().getX(), (int) sense.getPos().getWorldVar().getY(), r_sense, r_sense);
    	g.setColor(Color.green);
    	g.drawOval((int) attackrange.getPos().getWorldVar().getX(), (int) attackrange.getPos().getWorldVar().getY(), r_attackrange, r_attackrange);

        if(cam.getBounds().collides(bounds)) { 
        	
            g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
            
			
            // Health Bar UI
            g.setColor(Color.red);
			g.fillRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().getY() - 5), 50, 5);
			g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
			
			g.setColor(Color.green);
            g.fillRect((int) (pos.getWorldVar().getX() + 7), (int) (pos.getWorldVar().getY() - 5), (int) (50 * healthPercent), 5);

        }
        
    }
    
    public boolean getDeath() {
    	return die;
    }
}
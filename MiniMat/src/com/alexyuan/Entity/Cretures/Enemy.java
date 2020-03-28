package com.alexyuan.Entity.Cretures;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class Enemy extends Creture{

	private AABB sense;
	private int r;
	
	public Enemy(SpriteSheet sprite, Vector2f origin, int size) {
		super(sprite, origin, size);
		
		acc = 1f;
		maxSpeed = 3f;
		r = 500;
		
		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setXOffset(12);
		bounds.setYOffset(40);
		
		sense = new AABB(new Vector2f(origin.getX() + size / 2 - r / 2, origin.getY() + size / 2 - r / 2), r);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.green);
		g.drawRect( (int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
		
		g.setColor(Color.blue);
		g.drawOval((int) sense.getPos().getWorldVar().getX(), (int) sense.getPos().getWorldVar().getY(), r, r);
		
		g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);	

	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		// TODO Auto-generated met hod stub
		
	}
	
	public void chase(Player player) {
        AABB playerBounds = player.getBounds();
        //&& !attackrange.colCircleBox(playerBounds)
        if (sense.colCircleBox(playerBounds)) {
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

	public void destroy() {
		
	}
	
	public void update(Player player) {
		super.update();
		
		chase(player);
		move();
		
		if(!fallen) {
			if(!tc.collisionTile(dx, 0)) {
				sense.getPos().addX(dx);
				pos.addX(dx);
			}
			
			if(!tc.collisionTile(0, dy)) {
				sense.getPos().addY(dy);
				pos.addY(dy);
			}
		}else destroy();
	}
	
	
	
}

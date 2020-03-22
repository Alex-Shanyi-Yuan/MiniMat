package com.alexyuan.Entity.Cretures;

import java.awt.Graphics2D;

import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class Player extends Creture{

	public Player(SpriteSheet sprite, Vector2f pos) {
		super(sprite ,pos, Creture.DEFAULT_CRETURE_SIZE);

	}

	@Override
	public void update() {
        super.update();

		move();
		pos.addX(dx);
		pos.addY(dy);
	}

	@Override
	public void render(Graphics2D g) {
//		g.setColor(Color.red);
//		g.fillRect((int) pos.getX(), (int) pos.getY(), size, size);
		
		g.drawImage(ani.getImage().getImage(), (int) pos.getX(), (int) pos.getY(), size, size, null);
	}
	
	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		if(!fallen) {
            if(key.getUp().isHoldDown()) {
                up = true;
            } else {
                up = false;
            }
            if(key.getDown().isHoldDown()) {
                down = true;
            } else {
                down = false;
            }
            if(key.getLeft().isHoldDown()) {
                left = true;
            } else {
                left = false;
            }
            if(key.getRight().isHoldDown()) {
                right = true;
            } else {
                right = false;
            }

//            if(key.attack.down && canAttack) {
//                attack = true;
//                attacktime = System.nanoTime();
//            } else {
//                if(!attacking) {
//                    attack = false;
//                }
//            }

            if(key.getShift().isHoldDown()) {
            	maxSpeed  = 8;
                //cam.setMaxSpeed(7);
            } else {
            	maxSpeed  = 4;
                //cam.setMaxSpeed(4);
            }

            if(up && down) {
                up = false;
                down = false;
            }

            if(right && left) {
                right = false;
                left = false;
            }
        	} else {
        		up = false;
	            down = false;
	            right = false;
	            left = false;
	            
	        }
	}

}

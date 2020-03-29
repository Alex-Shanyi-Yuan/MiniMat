package com.alexyuan.Entity.Cretures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.States.PlayState;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class Player extends Creture{

	private ArrayList<Enemy> enemy;
	
	public Player(SpriteSheet sprite, Vector2f pos) {
		super(sprite ,pos, Creture.DEFAULT_CRETURE_SIZE);
		acc = 2f;
		maxSpeed = 4f;
		deacc = 0.3f;
		
		damage = 10;
		
		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setXOffset(12);
		bounds.setYOffset(40);
		
		hitBounds.setWidth(42);
        hitBounds.setHeight(42);
        
        ani.setNumFrames(4, UP);
        ani.setNumFrames(4, DOWN);
        ani.setNumFrames(4, ATTACK + RIGHT);
        ani.setNumFrames(4, ATTACK + LEFT);
        ani.setNumFrames(4, ATTACK + UP);
        ani.setNumFrames(4, ATTACK + DOWN);
        
        hasIdle = false;
        health = 500;
		maxHealth = 500;
		
		enemy = new ArrayList<Enemy>();
	}

	public void setTargetEnemy(Enemy enemy) { 
        this.enemy.add(enemy);
    }
	
	public void update(double time) {
        super.update(time);

        attacking = isAttacking(time);
        
        for(int i = 0; i < enemy.size(); i++) {
            if(attacking) {
                enemy.get(i).setHealth(enemy.get(i).getHealth() - damage, force * getDirection(), currentDirection == UP || currentDirection == DOWN);
                enemy.remove(i);
            }
        }
        
        if(!fallen) {
			move();
			if(!tc.collisionTile(dx, 0)) {
				pos.addX(dx);
				xCol = false;
			}
			else 
				xCol = true;
			if(!tc.collisionTile(0, dy)) {
				pos.addY(dy);
				yCol = false;
			}
			else
				yCol = true;
        }else if(ani.hasPlayedOnce()) {
        	xCol = true;
        	yCol = true;
        	resetPosition();
        	fallen = false;
        }
	}

	private void resetPosition() {
		pos.setX(600 - 32);
		PlayState.getMap().setX(0);
		PlayState.getCam().getPos().setX(0);
		pos.setY(350 - 32);
		PlayState.getMap().setY(0);
		PlayState.getCam().getPos().setY(0);
		
		dx = 0;
		dy = 0;
		
		setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
	}
	
	@Override
	public void render(Graphics2D g) {		
		g.setColor(Color.green);
		
		if(attack) {
			g.setColor(Color.red);
			g.drawRect((int) (hitBounds.getPos().getWorldVar().getX() + hitBounds.getXOffset()), (int) (hitBounds.getPos().getWorldVar().getY() + hitBounds.getYOffset()), (int) hitBounds.getWidth(), (int) hitBounds.getHeight());
		}
		g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
		g.drawImage(ani.getImage().getImage(), (int) pos.getWorldVar().getX(), (int)pos.getWorldVar().getY(), size, size, null);
	}
	
	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		
		key.getAttack().tick();
		
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
            if(key.getAttack().isClicked() && canAttack) {
                attack = true;
                attackTime = System.nanoTime();
            } else {
                if(!attacking) {
                    attack = false;
                }
            }
            if(key.getShift().isHoldDown()) {
            	maxSpeed  = 6;
                PlayState.getCam().updateCam(acc, maxSpeed, deacc);
            } else {
            	maxSpeed  = 4;
                PlayState.getCam().updateCam(acc, maxSpeed, deacc);
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
	
	public Vector2f getPos() {
		return pos;
	}

}

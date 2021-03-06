package com.alexyuan.Entity.Cretures.Player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import com.alexyuan.GamePanel;
import com.alexyuan.Entity.Cretures.Creture;
import com.alexyuan.Entity.Cretures.Enemy.Enemy;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.States.GameStateManager;
import com.alexyuan.States.PlayState;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class Player extends Creture{

	private boolean F = false, one = false, two = false, enter = false;
	private ArrayList<Enemy> enemy;
	
	public Player(SpriteSheet sprite, Vector2f pos, ArrayList<Enemy> enemy) {
		super(sprite ,pos, Creture.DEFAULT_CRETURE_SIZE);
		
		this.enemy = enemy;
		
		ATTACK = 5;
	    FALLEN = 4;
	    UP = 3;
	    DOWN = 2;
	    LEFT = 1;
	    RIGHT = 0;
	    IDLE = 6;
	    ANIMATIONSPEED = 5;
	    
		acc = 2f;
		maxSpeed = 4f;
		deacc = 0.3f;
		force = 25f;
		
		attackDuration = 500;
		attackSpeed = 525;
		damage = 500;
		
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
	}
	
	public void update(double time) {
        super.update(time);
        
        animate();
        attacking = isAttacking(time);
        
        for(int i = 0; i < enemy.size(); i++)
            if(attacking && hitBounds.collides(enemy.get(i).getBounds()))
                enemy.get(i).setHealth(enemy.get(i).getHealth() - damage, force * getDirection(), currentDirection == UP || currentDirection == DOWN);
        
        if(!fallen) {
            move();
            if(!tc.collisionTile(dx, 0)) {
                pos.addX(dx);
                xCol = false;
            } else {
                xCol = true;
            }
            if(!tc.collisionTile(0, dy) ) {
                pos.addY(dy);
                yCol = false;
            } else {
                yCol = true;
            }

            tc.normalTile(dx, 0);
            tc.normalTile(0, dy);

        } else{
            xCol = true;
            yCol = true;
                        
            if(ani.hasPlayedOnce()) {
                resetPosition();
                dx = 0;
                dy = 0;
                fallen = false;
                
                if(this.getHealth() <= 0) {
                	die = true;
                	PlayState.getGsm().getAudio().getEffects()[PlayState.getGsm().getAudio().PLAYERDIE].start();
                }
            }
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
	//TODO: FIX MAP WHEN PAUSED
	public void input(MouseHandler mouse, KeyHandler key) {
		key.getF().tick();
		key.getAttack().tick();
		key.getOne().tick();
		key.getTwo().tick();
		key.getEnter().tick();
		key.getEscape().tick();
		
		if(key.getEscape().isClicked()) {
        	if(PlayState.isPause()) 
				PlayState.getGsm().pop(PlayState.getGsm().PAUSE);
			else 
				PlayState.getGsm().add(PlayState.getGsm().PAUSE);

			PlayState.setPause(!PlayState.isPause());
		}
		
		if(!fallen) {
			if(key.getEnter().isClicked()) {
				enter = true;
			} else {
				enter = false;
			}
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
            if(key.getF().isClicked()) {
            	F = true;
            }else {
            	F = false;
            }
            if(key.getOne().isClicked()) {
            	one = true;
            }else {
            	one = false;
            }
            if(key.getTwo().isClicked()) {
            	two = true;
            }else {
            	two = false;
            }
            if(key.getAttack().isClicked() && canAttack) {
            	PlayState.getGsm().getAudio().getEffects()[PlayState.getGsm().getAudio().SWORDATTACK].start();
                attack = true;
                attackTime = System.nanoTime();
            } else {
                if(!attacking) {
					PlayState.getGsm().getAudio().getEffects()[PlayState.getGsm().getAudio().SWORDATTACK].setMicrosecondPosition(0);
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

	public boolean getDeath() {
		return die;
	}

	@Override
	public void animate() {
		if(attacking && !fallen) {
            if(currentAnimation < 5) {
                setAnimation(currentAnimation + ATTACK, sprite.getSpriteArray(currentAnimation + ATTACK), attackDuration / 100);
            }
        } else if (up) {
            if ((currentAnimation != UP || ani.getDelay() == -1)) {
                setAnimation(UP, sprite.getSpriteArray(UP), ANIMATIONSPEED);
            }
        } else if (down) {
            if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), ANIMATIONSPEED);
            }
        } else if (left) {
            if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), ANIMATIONSPEED);
            }
        } else if (right) {
            if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), ANIMATIONSPEED);
            }
        } else if (fallen) {
            if (currentAnimation != FALLEN || ani.getDelay() == -1) {
                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);
            }
        }
        else {
            if(!attacking && currentAnimation > 4) {
                setAnimation(currentAnimation - ATTACK, sprite.getSpriteArray(currentAnimation - ATTACK), -1);
            } else if(!attacking) {
                if(hasIdle && currentAnimation != IDLE) {
                    setAnimation(IDLE, sprite.getSpriteArray(IDLE), 10);
                } else if(!hasIdle) {
                    setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
                }
            }
        }
	}
	
	public boolean getF() {
		return F;
	}
	
	public boolean getOne() {
		return one;
	}
	
	public boolean getTwo() {
		return two;
	}
	
	public boolean getEnter() {
		return enter;
	}

	public void setDamage(int i) {
		damage = i;	
	}
	
	public int getDamage() {
		return damage;
	}
}

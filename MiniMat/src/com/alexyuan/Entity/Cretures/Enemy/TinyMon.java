package com.alexyuan.Entity.Cretures.Enemy;

import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.Camera;

public class TinyMon extends Enemy {
	
	public TinyMon(Camera cam, Vector2f origin, int size) {
		super(cam, Textures.getMon(), origin, size);

        damage = 10;
        acc = 1f;
        deacc = 2f;
        maxSpeed = 2f;
        
        r_attackrange = 40;
        r_sense = 300;
        sense = new AABB(new Vector2f(pos.getX() + size / 2 - r_sense / 2, pos.getY() + size / 2 - r_sense / 2), r_sense);
        attackrange = new AABB(new Vector2f(pos.getX() + bounds.getXOffset() + bounds.getWidth() / 2 - r_attackrange / 2 , pos.getY() + bounds.getYOffset() + bounds.getHeight() / 2 - r_attackrange / 2 ), r_attackrange);
        
        attackSpeed = 500;
        attackDuration = 500;
        force = 3f;
		
        bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setXOffset(12);
		bounds.setYOffset(40);

		healthLength = 50;
        maxHealth = 300;
        health = 300;

        ATTACK = 1;
	    FALLEN = 6;
	    UP = 5;
	    DOWN = 13;
	    LEFT = 9;
	    RIGHT = 1;
	    ANIMATIONSPEED = 5;

	    ani.setNumFrames(5, UP);
	    ani.setNumFrames(5, DOWN);
	    ani.setNumFrames(7, LEFT + ATTACK);
	    ani.setNumFrames(7, RIGHT + ATTACK);
	    ani.setNumFrames(7, LEFT + FALLEN);
	    ani.setNumFrames(7, RIGHT + FALLEN);
	}

	@Override
	public void animate() {
		if(attacking && !fallen) {
			if(currentAnimation == RIGHT || currentAnimation == DOWN) {
				if ((currentAnimation != RIGHT + ATTACK || ani.getDelay() == -1))
					setAnimation(RIGHT + ATTACK, sprite.getSpriteArray(RIGHT + ATTACK), attackDuration / 100);
			}else if (currentAnimation == LEFT || currentAnimation == UP) {
				if ((currentAnimation != LEFT + ATTACK || ani.getDelay() == -1))
					setAnimation(LEFT + ATTACK, sprite.getSpriteArray(LEFT + ATTACK), attackDuration / 100);
			}
		} else if (left) {
            if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), ANIMATIONSPEED);
            }
        } else if (right) {
            if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), ANIMATIONSPEED);
            }
        }else if (up) {
            if ((currentAnimation != UP || ani.getDelay() == -1)) {
                setAnimation(UP, sprite.getSpriteArray(UP), ANIMATIONSPEED);
            }
        } else if (down) {
            if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), ANIMATIONSPEED);
            }
        } else if (fallen) {
        	if(currentAnimation == RIGHT || currentAnimation == DOWN) {
				if ((currentAnimation != RIGHT + FALLEN || ani.getDelay() == -1))
					setAnimation(RIGHT + FALLEN, sprite.getSpriteArray(RIGHT + FALLEN), 15);
			}else if (currentAnimation == LEFT || currentAnimation == UP) {
				if ((currentAnimation != LEFT + FALLEN || ani.getDelay() == -1))
					setAnimation(LEFT + FALLEN, sprite.getSpriteArray(LEFT + FALLEN), 15);
			}
        }else if(dx == 0 && dy == 0) {
            if(hasIdle && currentAnimation != IDLE) {
                setAnimation(IDLE, sprite.getSpriteArray(IDLE), 10);
            } else {
                setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
            }
        }
	}
}

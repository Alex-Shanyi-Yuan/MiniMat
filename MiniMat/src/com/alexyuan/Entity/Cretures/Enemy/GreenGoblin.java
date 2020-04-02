package com.alexyuan.Entity.Cretures.Enemy;

import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.Camera;

public class GreenGoblin extends Enemy{

	public GreenGoblin(Camera cam, Vector2f origin, int size) {
        super(cam, Textures.getGreenGoblin(), origin, size);
        xOffset = size / 4;
        yOffset = size / 4;

        damage = 10;
        acc = 1f;
        deacc = 2f;
        maxSpeed = 2f;
        
        attackSpeed = 500;
        attackDuration = 500;
		
        bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setXOffset(12);
		bounds.setYOffset(40);
		
		hitBounds.setWidth(42);
        hitBounds.setHeight(42);
        
        maxHealth = 100;
        health = 100;

        ATTACK = 4;
	    FALLEN = 2;
	    UP = 1;
	    DOWN = 2;
	    LEFT = 3;
	    RIGHT = 2;
	    IDLE = 0;
	    ANIMATIONSPEED = 5;

        hasIdle = false;

        currentAnimation = 0;
    }
	
	@Override
	public void animate() {
		
		if(attacking) {
			if(currentAnimation == RIGHT || currentAnimation == DOWN) {
				if ((currentAnimation != RIGHT + ATTACK || ani.getDelay() == -1))
					setAnimation(RIGHT + ATTACK, sprite.getSpriteArray(RIGHT + ATTACK), attackDuration / 100);
			}else if (currentAnimation == LEFT || currentAnimation == UP) {
				if ((currentAnimation != LEFT + ATTACK || ani.getDelay() == -1))
					setAnimation(LEFT + ATTACK, sprite.getSpriteArray(LEFT + ATTACK), attackDuration / 100);
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
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), ANIMATIONSPEED - 2);
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
	}
}

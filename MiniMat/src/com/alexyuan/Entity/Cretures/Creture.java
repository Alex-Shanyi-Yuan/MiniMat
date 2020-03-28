package com.alexyuan.Entity.Cretures;

import com.alexyuan.Entity.Entity;
import com.alexyuan.Graphics.Animation;
import com.alexyuan.LoadFile.Sprite;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.TileCollision;

public abstract class Creture extends Entity {

	public static final int DEFAULT_HEALTH = 100;
	public static final int DEFAULT_CRETURE_SIZE = 64;
	
	public static final int ATTACK = 5;
    public static final int FALLEN = 4;
    public static final int UP = 3;
    public static final int DOWN = 2;
    public static final int LEFT = 1;
    public static final int RIGHT = 0;
    
    protected boolean up, down, left, right, attack, attacking, canAttack, fallen, xCol, yCol;
	
    protected TileCollision tc;
    
    protected float maxSpeed;
	protected int health;
	protected float speed;
	protected float dx, dy;
	protected float acc;
	protected float deacc = 0.3f;
	protected int currentAnimation;
	protected int currentDirection = RIGHT;
	
	protected AABB hitBounds;
	
	public Creture(SpriteSheet sprite, Vector2f origin, int size) {
		super(sprite, origin, size);
		
		hitBounds = new AABB(origin, size, size);
		hitBounds.setXOffset(size / 2);
		
		tc = new TileCollision(this);
		ani = new Animation();
		setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
		
		health = DEFAULT_HEALTH;
		speed = maxSpeed;
		
		dx = 0;
		dy = 0;
	}
	
	public void setAnimation(int i, Sprite[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(i, frames);
        ani.setDelay(delay);
    }

	public void animate() {
		
		if (left) {
            if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else if (right) {
            if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }else if (up) {
            if ((currentAnimation != UP || ani.getDelay() == -1)) {
                setAnimation(UP, sprite.getSpriteArray(UP), 10);
            }
        } else if (down) {
            if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 10);
            }
        } else if (fallen) {
            if (currentAnimation != FALLEN || ani.getDelay() == -1) {
                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);
            }
        }
        else {
//            if(!attacking && currentAnimation > 4) {
//                setAnimation(currentAnimation - ATTACK, sprite.getSpriteArray(currentAnimation - ATTACK), -1);
//            } else if(!attacking) {
//                if(hasIdle && currentAnimation != IDLE) {
//                    setAnimation(IDLE, sprite.getSpriteArray(IDLE), 10);
//                } else if(!hasIdle) {
                    setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
                }
//            }
        }
//	}
	
	public void move() {
		
		if(up) {
            currentDirection = UP;
            dy -= acc;
            if(dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if(dy < 0) {
                dy += deacc;
                if(dy > 0) {
                    dy = 0;
                }
            }
        }
		
		if(down) {
	        currentDirection = DOWN;
	        dy += acc;
	        if(dy > maxSpeed) {
	            dy = maxSpeed;
	        }
	    } else {
	        if(dy > 0) {
	            dy -= deacc;
	            if(dy < 0) {
	                dy = 0;
	            }
	        }
	    }

	    if(left) {
	        currentDirection = LEFT;
	        dx -= acc;
	        if(dx < -maxSpeed) {
	            dx = -maxSpeed;
	        }
	    } else {
	        if(dx < 0) {
	            dx += deacc;
	            if(dx > 0) {
	                dx = 0;
	            }
	        }
	    }

	    if(right) {
	        currentDirection = RIGHT;
	        dx += acc;
	        if(dx > maxSpeed) {
	            dx = maxSpeed;
	        }
	    } else {
	        if(dx > 0) {
	            dx -= deacc;
	            if(dx < 0) {
	                dx = 0;
	            }
	        }
	    }
	}

	private void setHitBoxDirection() {
        if (up && !attacking) {
            hitBounds.setXOffset((size - hitBounds.getWidth()) / 2);
            hitBounds.setYOffset(-hitBounds.getHeight() / 2 + hitBounds.getXOffset());
        } else if (down && !attacking) {
            hitBounds.setXOffset((size - hitBounds.getWidth()) / 2);
            hitBounds.setYOffset(hitBounds.getHeight() / 2 + hitBounds.getXOffset());
        } else if (left && !attacking) {
            hitBounds.setYOffset((size - hitBounds.getHeight()) / 2);
            hitBounds.setXOffset(-hitBounds.getWidth() / 2 + hitBounds.getYOffset()); 
        } else if (right && !attacking) {
            hitBounds.setYOffset((size - hitBounds.getHeight()) / 2);
            hitBounds.setXOffset(hitBounds.getWidth() / 2 + hitBounds.getYOffset());
        }
    }
	
	public void setFallen(boolean b) {
		fallen = b;
	}
	
	public AABB getBounds() {
		return bounds;
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public float getDx() {
		return dx;
	}
	
	public float getDy() {
		return dy;
	}
	
	public float getAcc() {
		return acc;
	}
	
	public float getSize() {
		return size;
	}
	
	public boolean getXCol() {
		return xCol;
	}
	
	public boolean getYCol() {
		return yCol;
	}
	
	public float getDeacc() {
		return deacc;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}
	public void update() {
        animate();
        ani.update();
        setHitBoxDirection();
    }
}

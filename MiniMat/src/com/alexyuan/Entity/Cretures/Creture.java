package com.alexyuan.Entity.Cretures;

import com.alexyuan.Entity.Entity;

public abstract class Creture extends Entity {

	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_SPEED = 5.0f;
	public static final int DEFAULT_CRETURE_WIDTH = 64, DEFAULT_CRETURE_HEIGHT = 64;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	protected float acceleration = 0.5f;
	
	public Creture(float x, float y, int width, int height) {
		super(x, y, width, height);

		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		x += xMove;
		y += yMove;
	}

}

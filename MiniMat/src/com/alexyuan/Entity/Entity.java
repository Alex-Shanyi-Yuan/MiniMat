package com.alexyuan.Entity;

import java.awt.Graphics2D;

import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public abstract class Entity {
	protected float x, y;
	protected int width, height;
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
	
	public abstract void input(MouseHandler mouse, KeyHandler key);
	
	
}

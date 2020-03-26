package com.alexyuan.Entity;

import java.awt.Graphics2D;

import com.alexyuan.Graphics.Animation;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;
import com.alexyuan.util.TileCollision;

public abstract class Entity {
	protected int size;
	protected Vector2f pos;
	protected Animation ani;
	protected SpriteSheet sprite;
	protected AABB bounds;
    
	public Entity(SpriteSheet sprite,Vector2f origin, int size) {
		this.bounds = new AABB(origin, size, size);
		this.pos = origin;
		this.size = size;
		this.sprite = sprite;
	}
	
	public abstract void render(Graphics2D g);
	
	public abstract void input(MouseHandler mouse, KeyHandler key);

	public abstract void update();
	
	public AABB getBounds() {
		return bounds;
	}
}

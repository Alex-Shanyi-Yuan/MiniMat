package com.alexyuan.Entity;

import java.awt.Graphics2D;

import com.alexyuan.Graphics.Animation;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public abstract class Entity {
	protected int size;
	protected Vector2f pos;
	protected Animation ani;
	protected SpriteSheet sprite;
	
	public static final int ATTACK = 5;
    public static final int FALLEN = 4;
    public static final int UP = 3;
    public static final int DOWN = 2;
    public static final int LEFT = 1;
    public static final int RIGHT = 0;
    
	public Entity(SpriteSheet sprite,Vector2f origin, int size) {
		this.pos = origin;
		this.size = size;
		this.sprite = sprite;
	}
	
	public abstract void render(Graphics2D g);
	
	public abstract void input(MouseHandler mouse, KeyHandler key);

	public void update() {
		
	}	
	
}

package com.alexyuan.Tiles.blocks;

import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Sprite;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;

public abstract class Block {
	
	protected int w, h;
	
	protected Sprite img;
	
	protected Vector2f pos;
	
	public Block(Sprite img, Vector2f pos, int w, int h) {
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }
	
	public abstract boolean update(AABB p);
	public abstract boolean isInside(AABB p);
	
	public void render(Graphics2D g) {
		g.drawImage(img.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h, null);
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
}

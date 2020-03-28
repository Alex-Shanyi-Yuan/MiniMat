package com.alexyuan.Tiles;

import java.awt.Graphics2D;

import com.alexyuan.Math.AABB;

public abstract class TileMap {
	
	public abstract void render(Graphics2D g, AABB cam);
}

package com.alexyuan.Tiles.blocks;

import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Sprite;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;

public class NormBlock extends Block {

	public NormBlock(Sprite sprite, Vector2f origin, int widht, int height) {
		super(sprite, origin, widht, height);
		
	}

	@Override
	public boolean update(AABB p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isInside(AABB p) {
		return false;
	}
	
	public void render(Graphics2D g) {
		super.render(g);
	}

}

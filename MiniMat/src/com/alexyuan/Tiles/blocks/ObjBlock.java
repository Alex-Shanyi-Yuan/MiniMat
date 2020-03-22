package com.alexyuan.Tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Sprite;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;

public class ObjBlock extends Block {

	public ObjBlock(Sprite img, Vector2f pos, int w, int h) {
		super(img, pos, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean update(AABB p) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		g.setColor(Color.white);
		g.drawRect((int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h);
	}

}

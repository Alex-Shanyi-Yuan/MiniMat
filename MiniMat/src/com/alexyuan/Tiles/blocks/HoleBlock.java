package com.alexyuan.Tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Sprite;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;

public class HoleBlock extends Block {

	public HoleBlock(Sprite img, Vector2f pos, int w, int h) {
		super(img, pos, w, h);
		// TODO Auto-generated constructor stub
	}

	public boolean update(AABB p) {
		System.out.println("in Hole");
		return false;
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		g.setColor(Color.green);
		g.drawRect((int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h);
	}

	public boolean isInside(AABB p) {

        if(p.getPos().getX() + p.getXOffset() < pos.getX()) return false;
        if(p.getPos().getY() + p.getYOffset() < pos.getY()) return false;
        if(w + pos.getX() < p.getWidth() + (p.getPos().getX() + p.getXOffset())) return false;
        if(h + pos.getY() < p.getHeight() + (p.getPos().getY() + p.getYOffset())) return false;
        
        return true;
    }
	
}
package com.alexyuan.Entity.Cretures;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class Player extends Creture{

	public Player(float x, float y) {
		super(x, y, Creture.DEFAULT_CRETURE_WIDTH, Creture.DEFAULT_CRETURE_HEIGHT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		move();
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		if(key.getRight().isHoldDown()) {
			if(xMove < 0)
				xMove = 0; 
			if(xMove < speed)
				xMove += acceleration;	
		}
		else if(key.getLeft().isHoldDown()) {
			if(xMove > 0)
				xMove = 0; 
			
			if(xMove > -speed)
				xMove -= acceleration;	
		}else xMove = 0;
		
		if(key.getUp().isHoldDown()) {
			if(yMove > 0)
				yMove = 0; 
			if(yMove > -speed)
				yMove -= acceleration;	
		}
		else if(key.getDown().isHoldDown()) {
			if(yMove < 0)
				yMove = 0; 
			
			if(yMove < speed)
				yMove += acceleration;	
		}else yMove = 0;
	}

}

package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics;

import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class PlayState extends GameState{

	private int x = 50, y = 50;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void updata() {
			
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x,y,100,100);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		if(key.getUp().isHoldDown())
			y --;
		if(key.getDown().isHoldDown())
			y++;
		if(key.getLeft().isHoldDown())
			x --;
		if(key.getRight().isHoldDown())
			x++;
		
	}


}

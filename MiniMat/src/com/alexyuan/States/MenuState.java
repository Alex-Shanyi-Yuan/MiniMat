package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics;

import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class MenuState extends GameState {
	
	int x = 50, y = 50;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void updata() {
		// TODO Auto-generated method stub

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

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fill3DRect(x, y, 100, 100, true);

	}

}

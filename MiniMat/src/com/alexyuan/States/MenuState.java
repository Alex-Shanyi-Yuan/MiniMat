package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics;

import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;
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
			y -= 2;
		if(key.getDown().isHoldDown())
			y+= 2;
		if(key.getLeft().isHoldDown())
			x -= 2;
		if(key.getRight().isHoldDown())
			x+= 2;

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.getMenuBack(), 0, 0, null);
		
		g.setColor(Color.gray);
		g.setFont(Fonts.getMagon1().deriveFont(100f));
		g.drawString("MINI MAT", 465, 150);
		g.setFont(Fonts.getMagon1().deriveFont(60f));
		g.drawString("- True Friendship -", 410, 225);
		

	}

}

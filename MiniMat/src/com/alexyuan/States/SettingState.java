package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.GamePanel;
import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class SettingState extends GameState {
	
	private boolean inBack = false;

	public SettingState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updata() {
		// TODO Auto-generated method stub

	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		if(mouse.getX() >= 20 && mouse.getX() <= 76 && mouse.getY() >= 605 && mouse.getY() <= 620) {
			inBack = true;
			
			if(mouse.getButton() == 1)
				gsm.addAndpop(gsm.MENU ,gsm.SETTING);
				
		}
		else
			inBack = false;

	}

	@Override
	public void render(Graphics2D g) {
		
		g.setColor(new Color(255,204,153));
		g.fillRect(0 ,0 , gsm.width, gsm.height);
		
		g.drawImage(Textures.getButtonBoard(), 350, 50, null);
		g.setColor(Color.black);
		g.setFont(Fonts.getMagon2().deriveFont(90f));
		g.drawString("Setting", 490, 140);
		
		g.setFont(Fonts.getMagon3().deriveFont(35f));
		if(inBack)
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.drawString("back", 20, 620);
	}

}

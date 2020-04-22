package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Audio;
import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class GameOverState extends GameState {

	private boolean win = false, inRange = false, clicked = false;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);

		if(PlayState.getPlayer().getDeath())
			win = false;
		else win = true;
	}

	@Override
	public void updata(double time) {
		if(clicked)
			gsm.addAndpop(gsm.MENU, gsm.GAMEOVER);
		else
			clicked = false;

	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		if(mouse.getX() >= 515 && mouse.getX() <= 683 && mouse.getY() >= 418 && mouse.getY() <= 437) {
			inRange = true;
			
			if(mouse.getButton() == 1)
				clicked = true;
		} else 
			inRange = false;

	}

	@Override
	public void render(Graphics2D g) {
		if(win) {
			g.drawImage(Textures.getOverWin(), 0, 0, 1185, 662, null);
			g.setColor(new Color(217,217,217));
		}
		else {
			g.drawImage(Textures.getOverFail(), 0, 0, 1185, 662, null);
			g.setColor(Color.white);
		}
		
		if(inRange)
			g.setColor(Color.red);
		
		g.setFont(Fonts.getMagon2().deriveFont(60f));
		g.drawString("Main Menu", 515, 437);
		
	}

}

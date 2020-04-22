package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.sound.sampled.Clip;

import com.alexyuan.LoadFile.Audio;
import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class MenuState extends GameState {
	
	private boolean inPlay = false, inStory = false, inExit = false,
					playClick = false, storyClick = false, exitClick = false;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void updata(double time) {
		
		if(gsm.isStateActive(gsm.STORY))
			return;
		
		if(playClick) {
			
			if(!gsm.isStateActive(gsm.PLAY)) {
				gsm.addAndpop(gsm.PLAY, gsm.MENU);
			}
			else {
				gsm.pop(gsm.MENU);
				PlayState.setPause(false);
				gsm.getAudio().getBackgrouds()[gsm.PLAY].setMicrosecondPosition(0);
				gsm.getAudio().getBackgrouds()[gsm.PLAY].start();
			}
			
			playClick = false;
		}else if(storyClick) {
			gsm.add(gsm.STORY);
			storyClick = false;
		}
		else if(exitClick)
			System.exit(0);
		
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		
		if(gsm.isStateActive(gsm.STORY))
			return;
		
		if(mouse.getX() >= 565 && mouse.getX() <= 630 && mouse.getY() >= 310 && mouse.getY() <= 330) {
			inPlay = true;
			
			if(mouse.getButton() == 1)
				playClick = true;
		}
		else
			inPlay = false;
		
		if(mouse.getX() >= 555 && mouse.getX() <= 640 && mouse.getY() >= 390 && mouse.getY() <= 410) {
			inStory = true;
			
			if(mouse.getButton() == 1)
				storyClick = true;
		}
		else
			inStory = false;
		
		if(mouse.getX() >= 575 && mouse.getX() <= 625 && mouse.getY() >= 470 && mouse.getY() <= 490) {
			inExit = true;
			
			if(mouse.getButton() == 1)
				exitClick = true;
		}
		else
			inExit = false;
	}

	@Override
	public void render(Graphics2D g) {
		
		if(gsm.isStateActive(gsm.STORY))
			return;
		
		g.drawImage(Textures.getMenuBack(), 0, -80, null);
		
		g.setColor(Color.gray);
		g.setFont(Fonts.getMagon1().deriveFont(100f));
		g.drawString("MINI MAT", 465, 150);
		g.setFont(Fonts.getMagon1().deriveFont(60f));
		g.drawString("- True Friendship -", 410, 225);
		
		g.setFont(Fonts.getMagon2().deriveFont(60f));
		
		if(inPlay)
			g.setColor(Color.red);
		else
			g.setColor(Color.gray);
		g.drawString("Play", 565, 330);
		
		if(inStory)
			g.setColor(Color.red);
		else
			g.setColor(Color.gray);
		g.drawString("Story", 555, 410);
		
		if(inExit)
			g.setColor(Color.red);
		else
			g.setColor(Color.gray);
		g.drawString("Exit", 575, 490);
		
	}

}

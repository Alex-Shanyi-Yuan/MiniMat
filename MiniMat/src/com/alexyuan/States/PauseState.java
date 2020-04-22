package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Audio;
import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class PauseState extends GameState {

	private boolean inResume = false, inStory = false, inMain = false,
					resumeClick = false, storyClick = false, mainClick = false;
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updata(double time) {
		
		if(gsm.isStateActive(gsm.STORY))
			return;
		
		if(resumeClick) {
			gsm.pop(gsm.PAUSE);
			PlayState.setPause(false);
		}
		else if(storyClick) {
			gsm.add(gsm.STORY);
			storyClick = false;
		}
		else if(mainClick) {
			gsm.addAndpop(gsm.MENU, gsm.PAUSE);
			gsm.getAudio().getBackgrouds()[gsm.PLAY].stop();
		}
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		
		if(gsm.isStateActive(gsm.STORY))
			return;
		
		if(mouse.getX() >= 545 && mouse.getX() <= 651 && mouse.getY() >= 258 && mouse.getY() <= 277) {
			inResume = true;
			if(mouse.getButton() == 1)
				resumeClick = true;
		}else inResume = false;
		
		if(mouse.getX() >= 555 && mouse.getX() <= 640 && mouse.getY() >= 338 && mouse.getY() <= 357) {
			inStory = true;
			if(mouse.getButton() == 1)
				storyClick = true;
		}else inStory = false;
		
		if(mouse.getX() >= 515 && mouse.getX() <= 684 && mouse.getY() >= 418 && mouse.getY() <= 437) {
			inMain = true;
			if(mouse.getButton() == 1)
				mainClick = true;
		}else inMain = false;

	}

	@Override
	public void render(Graphics2D g) {
		
		if(gsm.isStateActive(gsm.STORY))
			return;
		
		g.setColor(new Color(0, 0, 0, 20));
		g.fillRect(0, 0, 1200, 700);
		
		g.drawImage(Textures.getBoard(), 450, 0, 300, 700, null);
		
		g.setFont(Fonts.getMagon2().deriveFont(60f));
		
		g.setColor(Color.black);
		g.drawString("@", 590, 210);
		
		if(inResume)
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.drawString("Resume", 545, 277);
		
		if(inStory)
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.drawString("Story", 555, 357);
		
		if(inMain)
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		g.drawString("Main Menu", 515, 437);
		
		g.setColor(Color.black);
		g.drawString("@", 590, 500);
	}

}

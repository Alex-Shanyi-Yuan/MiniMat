package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Audio;
import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class StoryState extends GameState {
	
	private boolean inRange = false, clicked = false;
	
	public StoryState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void input(MouseHandler mouse, KeyHandler key) {
		if(mouse.getX() >= 140 && mouse.getX() <= 200 && mouse.getY() >= 485 && mouse.getY() <= 500) {
			inRange = true;
			if(mouse.getButton() == 1)
				clicked = true;
		}else
			inRange = false;

	}

	@Override
	protected void render(Graphics2D g) {
		g.drawImage(Textures.getStoryBack(), 0, 0, null);
		g.setFont(Fonts.getMagon2().deriveFont(30f));
		g.setColor(Color.black);
		g.drawImage(Textures.getBoard(), 0, -10, 1200, 710, null);	
		g.drawString("In a very drak night, Your friend is alone walking on the street.", 140, 220);
		g.drawString("Suddenly, a Tauren appeared and Knocked out your friend.", 140, 260);
		g.drawString("When your friend woke up, he relised the photo you gave him is gone!", 140, 300);
		g.drawString("You are very mad that monsters are bullying your friend so...", 140, 340);
		g.drawString("You decided to go get the photo back yourself, GOOD LUCK!", 140, 380);
		
		g.setFont(Fonts.getMagon2().deriveFont(50f));
		if(inRange)
			g.setColor(Color.red);
		g.drawString("back", 140, 500);
		
		g.drawImage(Textures.getInfo(), 730,210, null);

	}

	@Override
	protected void updata(double time) {
		if(clicked) {
			gsm.pop(gsm.STORY);	
			clicked = false;
		}
	}

}

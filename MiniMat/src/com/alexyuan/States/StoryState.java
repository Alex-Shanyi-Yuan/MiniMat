package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Textures;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class StoryState extends GameState {

	private boolean done = false;
	
	public StoryState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void input(MouseHandler mouse, KeyHandler key) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void render(Graphics2D g) {
		g.drawImage(Textures.getStoryBack(), 0, 0, null);
		
		if(!done) {
			try {
				g.setColor(Color.black);
				g.drawImage(Textures.getBoard(), 0, 0, 1200, 700, null);
				Thread.sleep(50);
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void updata(double time) {
		// TODO Auto-generated method stub

	}

}

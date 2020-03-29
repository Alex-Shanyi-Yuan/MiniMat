package com.alexyuan.States;

import java.awt.Graphics2D;

import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void updata(double time);
	
	public abstract void input(MouseHandler mouse, KeyHandler key);

	public abstract void render(Graphics2D g);
	
}

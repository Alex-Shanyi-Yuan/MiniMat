package com.alexyuan.States;

import java.awt.Graphics2D;

import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public abstract class GameState {
	protected static GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	protected abstract void input(MouseHandler mouse, KeyHandler key);

	protected abstract void render(Graphics2D g);

	protected abstract void updata(double time);
	
	public static GameStateManager getGsm() {
		return gsm;
	}
}

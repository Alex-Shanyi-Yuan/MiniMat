package com.alexyuan.States;

public abstract class GameState {
	
	public GameState() {}
	
	public abstract void updata();
	
	public abstract void render();
	
	public abstract void input();
	
}

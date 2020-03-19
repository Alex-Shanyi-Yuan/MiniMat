package com.alexyuan.States;

import java.awt.Graphics2D;

import com.alexyuan.Entity.Cretures.Player;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class PlayState extends GameState{

	private int playerX = 50, playerY = 50;
	private Player player;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		player = new Player(playerX, playerY);
		
	}

	@Override
	public void updata() {
		player.update();
	}

	@Override
	public void render(Graphics2D g) {
		player.render(g);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);		
	}


}

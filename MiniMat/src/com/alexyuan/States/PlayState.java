package com.alexyuan.States;

import java.awt.Graphics2D;

import com.alexyuan.Entity.Cretures.Enemy;
import com.alexyuan.Entity.Cretures.Player;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.Tiles.TileManager;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class PlayState extends GameState{

	private Player player;
	private TileManager tm;
	private Enemy littleGirl;
	private static Vector2f map;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		map = new Vector2f();
		Vector2f.setWorldVar(map.getX(), map.getY());
		
		tm = new TileManager("resources/TiledMap/tilemap.xml");
		player = new Player(Textures.getPlayer(),new Vector2f(600 - 32,350 - 32));
		littleGirl = new Enemy(Textures.getGirl(), new Vector2f(600 - 32 + 150,350 - 32 + 150), 64);
		
	}

	@Override
	public void updata() {
		Vector2f.setWorldVar(map.getX(), map.getY());
		player.update();
		littleGirl.update(player);
	}

	@Override
	public void render(Graphics2D g) {
		tm.render(g);
		player.render(g);
		littleGirl.render(g);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);		
	}
	
	public static Vector2f getMap() {
		return map;
	}


}

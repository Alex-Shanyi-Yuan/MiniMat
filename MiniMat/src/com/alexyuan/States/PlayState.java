package com.alexyuan.States;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.Entity.Cretures.Enemy;
import com.alexyuan.Entity.Cretures.Player;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.Tiles.TileManager;
import com.alexyuan.util.Camera;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class PlayState extends GameState{

	private Player player;
	private TileManager tm;
	private Enemy littleGirl;
	private static Vector2f map;
	private static Camera cam;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		map = new Vector2f();
		Vector2f.setWorldVar(map.getX(), map.getY());
		
		cam = new Camera(new AABB(new Vector2f(0, 0), 1264, 764));
		
		tm = new TileManager("resources/TiledMap/tilemap.xml", cam);
		player = new Player(Textures.getPlayer(),new Vector2f(600 - 32,350 - 32));
		littleGirl = new Enemy(Textures.getGirl(), new Vector2f(600 - 32 + 150,350 - 32 + 150), 64);
		
		cam.target(player);
	}

	@Override
	public void updata() {
		Vector2f.setWorldVar(map.getX(), map.getY());
		player.update(littleGirl);
		cam.update();
		littleGirl.update(player);
	}

	@Override
	public void render(Graphics2D g) {		
		tm.render(g);
		cam.render(g);
		player.render(g);
		littleGirl.render(g);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);		
		cam.input(mouse, key);
	}
	
	public static Vector2f getMap() {
		return map;
	}

	public static Camera getCam() {
		return cam;
	}
}

package com.alexyuan.States;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.Entity.Cretures.Player;
import com.alexyuan.Entity.Cretures.Enemy.Enemy;
import com.alexyuan.Entity.Cretures.Enemy.TinyMan;
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
	
	private final int FULL = 0, TF = 1, HALF = 2, OF = 3, EMPTY = 4;
	
	private static Vector2f map;
	private static Camera cam;
	
	private ArrayList<Enemy> enemy;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		map = new Vector2f();
		Vector2f.setWorldVar(map.getX(), map.getY());
		
		
		cam = new Camera(new AABB(new Vector2f(-1, -1), 1232, 732));
		
		tm = new TileManager("resources/TiledMap/tilemap.xml", cam);
		
		enemy = new ArrayList<Enemy>();
		enemy.add(new TinyMan(cam, Textures.getGirl(), new Vector2f(1000,800), 64));
		
		player = new Player(Textures.getPlayer(),new Vector2f(600 - 32,350 - 32), enemy);
		
		cam.target(player);
	}

	public void updata(double time) {
		Vector2f.setWorldVar(map.getX(), map.getY());
		player.update(time);
		cam.update();
		
		for(Enemy e : enemy)
			e.update(player, time);
	}

	@Override
	public void render(Graphics2D g) {		
		tm.render(g);
		player.render(g);
		
		for(Enemy e : enemy)
			e.render(g);
		
		System.out.println(player.getHealthPercent());
		for(int i = 1; i <= Textures.getHeart().getSpriteArray(0).length; i++)
			if(player.getHealthPercent() * 100  >= i * 20)
				g.drawImage(Textures.getHeart().getSpriteArray(0)[FULL].getImage(), i*32, 32, 32, 32, null);
			else if(player.getHealthPercent() * 100  >= (((i - 1) * 20) + (20  / (4.0/3)))) 
				g.drawImage(Textures.getHeart().getSpriteArray(0)[TF].getImage(), i*32, 32, 32, 32, null);
			else if(player.getHealthPercent() * 100  >= (((i - 1) * 20) + (20  / 2.0)))
				g.drawImage(Textures.getHeart().getSpriteArray(0)[HALF].getImage(), i*32, 32, 32, 32, null);
			else if(player.getHealthPercent() * 100  > (i - 1) * 20) 
				g.drawImage(Textures.getHeart().getSpriteArray(0)[OF].getImage(), i*32, 32, 32, 32, null);
			else
				g.drawImage(Textures.getHeart().getSpriteArray(0)[EMPTY].getImage(), i*32, 32, 32, 32, null);
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

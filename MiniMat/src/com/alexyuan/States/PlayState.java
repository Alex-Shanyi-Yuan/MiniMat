package com.alexyuan.States;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.Entity.Chest;
import com.alexyuan.Entity.Entity;
import com.alexyuan.Entity.Potion;
import com.alexyuan.Entity.Cretures.Enemy.Enemy;
import com.alexyuan.Entity.Cretures.Enemy.GreenGoblin;
import com.alexyuan.Entity.Cretures.Enemy.Minotaur;
import com.alexyuan.Entity.Cretures.Enemy.PurpleGoblin;
import com.alexyuan.Entity.Cretures.Enemy.TinyMon;
import com.alexyuan.Entity.Cretures.Player.Player;
import com.alexyuan.Entity.Cretures.Player.PlayerUI;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.Tiles.TileManager;
import com.alexyuan.util.Camera;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class PlayState extends GameState{

	private PlayerUI playerUI;
	private Player player;
	private TileManager tm;
	
	private static Vector2f map;
	private static Camera cam;
	
	private ArrayList<Enemy> enemy;
	private ArrayList<Chest> chest;
	private ArrayList<Potion> potion;
			
	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		map = new Vector2f();
		Vector2f.setWorldVar(map.getX(), map.getY());
		
		
		cam = new Camera(new AABB(new Vector2f(-1, -1), 1232, 732));
		playerUI = new PlayerUI();
		tm = new TileManager("resources/TiledMap/tilemap.xml", cam);
		
		potion = new ArrayList<Potion>();
		
		enemy = new ArrayList<Enemy>();
		enemy.add(new GreenGoblin(cam, new Vector2f(1920,576), 64));
		enemy.add(new PurpleGoblin(cam, new Vector2f(1664 ,576), 64));
		enemy.add(new GreenGoblin(cam, new Vector2f(2240,960), 64));
		enemy.add(new PurpleGoblin(cam, new Vector2f(2240 ,768), 64));
		enemy.add(new GreenGoblin(cam, new Vector2f(2240,1344), 64));
		enemy.add(new PurpleGoblin(cam, new Vector2f(2240 ,1152), 64));
		enemy.add(new Minotaur(cam, new Vector2f(1216, 1152), 128));
		enemy.add(new TinyMon(cam, new Vector2f(512, 2112), 64));
		enemy.add(new TinyMon(cam, new Vector2f(1600, 2112), 64));
		enemy.add(new TinyMon(cam, new Vector2f(2368, 2112), 64));
		enemy.add(new TinyMon(cam, new Vector2f(2688, 2112), 64));
		
		chest = new ArrayList<Chest>();
		chest.add(new Chest(new Vector2f(950, 1300), 192, potion, 1));
		chest.add(new Chest(new Vector2f(2112, 1546), 192, potion, 2));
		chest.add(new Chest(new Vector2f(384, 2752), 192, potion, 3));
		chest.add(new Chest(new Vector2f(1472, 2752), 192, potion, 4));
		chest.add(new Chest(new Vector2f(2688, 2752), 192, potion, 5));
		
		player = new Player(Textures.getPlayer(),new Vector2f(600 - 32,350 - 32), enemy);
		
		cam.target(player);
	}

	public void updata(double time) {
		Vector2f.setWorldVar(map.getX(), map.getY());
		cam.update();
		playerUI.update(player);
		
		if(player.getDeath())
			gsm.addAndpop(gsm.GAMEOVER, gsm.PLAY);
		else 
			player.update(time);
		
		for(int i = 0; i < enemy.size(); i++)
			if(enemy.get(i).getDeath())
				enemy.remove(i);
			else 
				enemy.get(i).update(player, time);
		
		for(Chest c : chest)
			c.update(player);
		
		for(int i = 0; i < potion.size(); i++)
			if(potion.get(i).getDeath())
				potion.remove(i);
			else
				potion.get(i).update(player);
	}

	@Override
	public void render(Graphics2D g) {		
		tm.render(g);
		
		for(Enemy e : enemy)
			e.render(g);
		
		for(Chest c : chest)
			c.render(g);
		
		for(Entity p : potion)
			p.render(g);
		
		player.render(g);
		playerUI.render(g, player);
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

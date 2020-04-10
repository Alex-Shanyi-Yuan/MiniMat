package com.alexyuan.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.Entity.Cretures.Player.Player;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.Vector2f;

public class Chest extends Entity {
	
	private ArrayList<Potion> potion;
	private int id;
	
	public Chest(Vector2f origin, int size, ArrayList<Potion> potion, int id) {
		super(Textures.getChest(), origin, size);
		
		this.potion = potion;
		this.id = id;
		
		bounds.setWidth((int) (64));
		bounds.setHeight((int) (64));
		bounds.setXOffset(65);
		bounds.setYOffset(65);
		
		setAnimation(0, sprite.getSpriteArray(0), -1);
		
	}

	@Override
	public void render(Graphics2D g) {
		
		g.setColor(Color.blue);
		g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
		
		g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
		
	}
	
	public void update(Player player) {
		super.update();
		
		if(player.bounds.collides(bounds) && player.getF() && !ani.hasPlayedOnce()) 
			INTERACTE = true;
		
		if(INTERACTE && !ani.hasPlayedOnce()) {
			if ((currentAnimation != 0 || ani.getDelay() == -1)) {
				setAnimation(0, sprite.getSpriteArray(0), 5);
			}
		} else if(ani.hasPlayedOnce()) {
			ani.setFrame(4);
			
			if(INTERACTE) {
				if(id == 2) {
					potion.add(new Potion(Textures.getPotionD(), new Vector2f(2100, 1644), "damage", 32));
					potion.add(new Potion(Textures.getPotionH(), new Vector2f(2156, 1644), "health", 32));
				}
				if(id == 1) {
					potion.add(new Potion(Textures.getPotionH(), new Vector2f(983, 1400), "health", 32));
					potion.add(new Potion(Textures.getPotionH(), new Vector2f(1047, 1400), "health", 32));
				}
				if(id == 3) {
					
				}
				if(id == 4) {
					potion.add(new Potion(Textures.getPotionH(), new Vector2f(1569, 2858), "health", 32));
				}
				if(id == 5) {
					potion.add(new Potion(Textures.getPotionD(), new Vector2f(2753, 2858), "damage", 32));
				}
			}
			
			INTERACTE = false;
		}
	}
}

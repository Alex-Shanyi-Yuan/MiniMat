package com.alexyuan.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.Entity.Cretures.Player.Player;
import com.alexyuan.Entity.Cretures.Player.PlayerUI;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.Vector2f;

public class Potion extends Entity {

	private String type;
	
	public Potion(SpriteSheet sprite, Vector2f origin, String type, int size) {		
		super(sprite, origin, size);
		
		bounds.setWidth((int) (32));
		bounds.setHeight((int) (32));
		bounds.setXOffset(0);
		bounds.setYOffset(0);
		
		this.type = type;
		setAnimation(0, sprite.getSpriteArray(0), 5);
	}

	@Override
	public void render(Graphics2D g) {
		
		g.setColor(Color.blue);
		g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
		
		g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
		
	}
	
	public void update(Player player) {
		super.update();
		
		if(player.bounds.collides(bounds) && player.getF()) 
			INTERACTE = true;
		
		if(INTERACTE) {
			if(type.equals("health"))
				PlayerUI.gainPotionH();
			
			if(type.equals("damage"))
				PlayerUI.gainPotionD();
			
			die = true;
		} else {
			if ((currentAnimation != 0 || ani.getDelay() == -1)) {
				setAnimation(0, sprite.getSpriteArray(0), 5);
			}
		}
		
	}
	
	public boolean getDeath() {
		return die;
	}

}

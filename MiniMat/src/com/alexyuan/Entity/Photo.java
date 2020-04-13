package com.alexyuan.Entity;

import java.awt.Graphics2D;

import com.alexyuan.Entity.Cretures.NPC.Friend;
import com.alexyuan.Entity.Cretures.Player.Player;
import com.alexyuan.Entity.Cretures.Player.PlayerUI;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.Vector2f;

public class Photo extends Entity{

	public Photo(Vector2f origin, int size) {
		super(Textures.getPhoto(), origin, size);
		
		bounds.setWidth(64);
		bounds.setHeight(64);
		
		setAnimation(0,sprite.getSpriteArray(0),-1);
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(ani.getImage().getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
	}
	
	public void update(Player player) {
		if(player.bounds.collides(bounds) && player.getF()) 
			INTERACTE = true;
		
		if(INTERACTE) {
			PlayerUI.gainPhoto();
			Friend.setHasItem(true);
			die = true;
		}
	}

}

package com.alexyuan.Entity.Cretures.Player;

import java.awt.Color;
import java.awt.Graphics;

import com.alexyuan.Entity.Cretures.NPC.Friend;
import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;

public class PlayerUI {
	
	private final int FULL = 0, TF = 1, HALF = 2, OF = 3, EMPTY = 4;
	private static int potionH = 0, potionD = 0, photo = 0;
	
	public void render(Graphics g, Player player) {
		
		g.setFont(Fonts.getMagon3().deriveFont(52f));
		g.setColor(Color.white);
		g.drawString(" X " + potionD, 80, 134);
		g.drawString(" X " + potionH, 80, 198);
		g.drawString(" X " + photo, 80, 262);
		g.setFont(Fonts.getMagon3().deriveFont(20f));
		g.drawString("1", 25, 133);
		g.drawString("2", 25, 197);
		
		g.drawImage(Textures.getPotionD().getSpriteArray(0)[0].getImage(), 15, 80, 64, 64, null);
		g.drawImage(Textures.getPotionH().getSpriteArray(0)[0].getImage(), 15, 144, 64, 64, null);
		g.drawImage(Textures.getPhoto().getSpriteArray(0)[0].getImage(), 15, 208, 64, 64, null);
		
		for(int i = 1; i <= Textures.getHeart().getSpriteArray(0).length; i++) {
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
	}
	
	public void update(Player player) {
		if(player.getTwo() && potionH > 0) {
			player.setHealth(player.getHealth() + 100, 0, true);
			potionH --;
			System.out.println(1);
		}
		
		if(player.getHealth() > 500) {
			player.setHealth(500, 0, true);
		}
		
		if(player.getOne() && potionD > 0) {
			player.setDamage(player.getDamage() + 100);
			System.out.println(2);
			potionD --;
		}
	}
	
	public static void gainPotionH() {
		potionH++;
	}
	
	public static void gainPotionD() {
		potionD++;
	}
	
	public static void gainPhoto() {
		photo++;
	}
	
}

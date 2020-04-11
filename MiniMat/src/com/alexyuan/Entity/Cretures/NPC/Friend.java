package com.alexyuan.Entity.Cretures.NPC;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.Camera;

public class Friend extends NPC{

	private boolean hasItem = false;
	
	public Friend(Camera cam, Vector2f origin, int size) {
		super(cam, Textures.getGirl(), origin, size);

		count = 0;
		
		maxSpeed = 0;
		
		bounds.setWidth(64);
		bounds.setHeight(64);

        ANIMATIONSPEED = -1;
        
        DOWN = 2;
        
        setAnimation(DOWN, sprite.getSpriteArray(DOWN), ANIMATIONSPEED);
	}

	@Override
	public void animate() {}

	@Override
	protected void text(Graphics2D g) {
		
		g.drawImage(Textures.getBoard(), 0, 480, 1200, 170, null);
		
		g.setColor(Color.black);
		g.setFont(Fonts.getMagon1().deriveFont(32f));
		
		if(hasItem) {
			if (count == 0)
				g.drawString("OMG ___ !!!", 110, 540);
			else if (count == 1)
				g.drawString("You found it!", 110, 540);
			else if (count == 2){
				g.drawString("I am so greatful!", 110, 540);
				g.drawString("I knew I could count on you, Sloim!", 110, 562);
			} else if (count == 3)
				g.drawString("Thank you so so much!" , 110,  540);
		} else {
			
			if(count == 0)
				g.drawString("Hello, Mr. Sloim, My dear Friend.", 110, 540);
			else if(count == 1) {
				g.drawString("Remenber the photo you gave me?", 110, 540);
				g.drawString("Well...", 110, 562);
			}else if(count == 2)
				g.drawString("I LOST IT (T_T)", 110, 540);
			else if(count == 3)
				g.drawString("Please help me find it as a prove to our friend-ship! Thx :)", 110,  540);
			else if(count == 4) {
				INTERACTE = false;
				count = 0;
			}
			
			g.setFont(Fonts.getMagon1().deriveFont(20f));
			g.drawString("-> Enter", 1040, 600);
		}
	}
}

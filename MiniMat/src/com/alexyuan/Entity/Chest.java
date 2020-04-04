package com.alexyuan.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.LoadFile.Textures;
import com.alexyuan.Math.Vector2f;

public class Chest extends Entity {
	
	private final int OPEN = 0;
	private static boolean open = false;

	public Chest(Vector2f origin) {
		super(Textures.getChest(), origin, 64);
		
		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setXOffset(12);
		bounds.setYOffset(40);
		
	}

	@Override
	public void render(Graphics2D g) {
		
		g.setColor(Color.blue);
		g.drawRect((int) bounds.getPos().getX(), (int) bounds.getPos().getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
		

		g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
			
		
	}
	
	public void update() {
		ani.setDelay(-1);
		if(open && !ani.hasPlayedOnce()) {
			if ((currentAnimation != OPEN || ani.getDelay() == -1))
				setAnimation(OPEN, sprite.getSpriteArray(OPEN), 5);
		} else if(ani.hasPlayedOnce()) {
			System.out.println("hi");
		}
		
	}
	
	public static boolean isOpen() {
		return open;
	}
	
	public static void setOpen(boolean b) {
		open = b;
	}

}

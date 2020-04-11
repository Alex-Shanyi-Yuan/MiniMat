package com.alexyuan.Entity.Cretures.NPC;

import java.awt.Color;
import java.awt.Graphics2D;

import com.alexyuan.Entity.Cretures.Creture;
import com.alexyuan.Entity.Cretures.Player.Player;
import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.Camera;

public abstract class NPC extends Creture{

	private Camera cam;
	protected int count;
	
	public NPC(Camera cam,SpriteSheet sprite, Vector2f origin, int size) {
		super(sprite, origin, size);
		this.cam = cam;
	}

	protected abstract void text(Graphics2D g);
	
	@Override
	public void render(Graphics2D g) {
    	g.setColor(Color.blue);
    	g.drawRect((int) bounds.getPos().getWorldVar().getX(), (int) bounds.getPos().getWorldVar().getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
        if(cam.getBounds().collides(bounds)) { 
            g.drawImage(ani.getImage().getImage(), (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
        }
		
        if(INTERACTE)
        	text(g);
        	
	}
	
	public void update(Player player, double time) {
		if(cam.getBounds().collides(this.bounds)) {
            super.update(time);
            this.animate();
            
		    if(player.getF() && bounds.collides(player.getBounds())) {
		    	INTERACTE = true;
		    }
		    
		    if(INTERACTE && player.getEnter())
		    	count++;
        }
	}
	
	public boolean getInteracte() {
		return INTERACTE;
	}

}

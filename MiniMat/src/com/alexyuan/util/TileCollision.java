package com.alexyuan.util;


import com.alexyuan.Entity.Cretures.Creture;
import com.alexyuan.Tiles.TileMapObj;
import com.alexyuan.Tiles.blocks.Block;
import com.alexyuan.Tiles.blocks.HoleBlock;

public class TileCollision {

	private Creture creture;
	private Block block;
	
	public TileCollision(Creture creture) {
		this.creture = creture;
	}
	
	
	public boolean collisionTile(float ax, float ay) {
        if(TileMapObj.getTwoBlock() != null) {
            int xt;
            int yt;

            for(int c = 0; c < 4; c++) {
                
                xt = (int) ( (creture.getBounds().getPos().getX() + ax) + (c % 2) * creture.getBounds().getWidth() + creture.getBounds().getXOffset()) / 64;
                yt = (int) ( (creture.getBounds().getPos().getY() + ay) + (c / 2) * creture.getBounds().getHeight() + creture.getBounds().getYOffset()) / 64;

                if(TileMapObj.getTwoBlock().containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                	Block block = TileMapObj.getTwoBlock().get(String.valueOf(xt) + "," + String.valueOf(yt));
                	if(block instanceof HoleBlock) 
                		return collisionHole(ax,ay, xt, yt, block);
                	
                	return block.update(creture.getBounds());
                }
                
            }
            
            return false;
        }

        return false;
    }
	
	private boolean collisionHole(float ax, float ay, float xt, float yt, Block block) {
		int nextXt = (int) ((( (creture.getBounds().getPos().getX() + ax) + creture.getBounds().getXOffset()) / 64) + creture.getBounds().getWidth() / 64);
		int nextYt = (int) ((( (creture.getBounds().getPos().getY() + ay) + creture.getBounds().getYOffset()) / 64) + creture.getBounds().getHeight() / 64);
		
		if(block.isInside(creture.getBounds())) {
			creture.setFallen(true);
			return false;
		}else if((nextYt == yt + 1 ) || (nextXt == xt + 1) || (nextYt == yt - 1) || (nextXt == xt - 1)) {
			if(TileMapObj.getTwoBlock().containsKey(String.valueOf(nextXt) + "," + String.valueOf(nextYt))) {
				if(creture.getBounds().getPos().getX() > block.getPos().getX())
					creture.setFallen(true);
			}
			return false;
		}
			
		creture.setFallen(false);
		return false;
		
	}
}

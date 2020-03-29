package com.alexyuan.util;


import com.alexyuan.Entity.Cretures.Creture;
import com.alexyuan.Tiles.TileMapObj;
import com.alexyuan.Tiles.blocks.Block;
import com.alexyuan.Tiles.blocks.HoleBlock;

public class TileCollision {

    private Creture e;
    private int tileId;

    public TileCollision(Creture e) {
        this.e = e;
    }

    public boolean normalTile(float ax, float ay) {
        int xt;
        int yt;

        xt = (int) ( (e.getPos().getX() + ax) + e.getBounds().getXOffset()) / 64;
        yt = (int) ( (e.getPos().getY() + ay) + e.getBounds().getYOffset()) / 64;
        tileId = (xt + (yt * TileMapObj.height));

        if(tileId > TileMapObj.height * TileMapObj.width) tileId = (TileMapObj.height * TileMapObj.width) - 2;

        return false;
    }

    public boolean collisionTile(float ax, float ay) {
        if(TileMapObj.getBlocks() != null) {
            int xt;
            int yt;

            for(int c = 0; c < 4; c++) {
                
                xt = (int) ( (e.getPos().getX() + ax) + (c % 2) * e.getBounds().getWidth() + e.getBounds().getXOffset()) / 64;
                yt = (int) ( (e.getPos().getY() + ay) + (c / 2) * e.getBounds().getHeight() + e.getBounds().getYOffset()) / 64;

                if(xt <= 0 || yt <= 0 || xt + (yt * TileMapObj.height) < 0 || xt + (yt * TileMapObj.height) > (TileMapObj.height * TileMapObj.width) - 2) {
                    return true;
                } 
                
                if(TileMapObj.getBlocks()[xt + (yt * TileMapObj.height)] instanceof Block) {
                    Block block = TileMapObj.getBlocks()[xt + (yt * TileMapObj.height)];
                    if(block instanceof HoleBlock) {
                        return collisionHole(ax, ay, xt, yt, block);
                    }
                    return block.update(e.getBounds());
                }
            }
        }

        return false;
    }

    public int getTile() { return tileId; }

    private boolean collisionHole(float ax, float ay, float xt, float yt, Block block) {
        int nextXt = (int) ((( (e.getPos().getX() + ax) + e.getBounds().getXOffset()) / 64) + e.getBounds().getWidth() / 64);
        int nextYt = (int) ((( (e.getPos().getY() + ay) + e.getBounds().getYOffset()) / 64) + e.getBounds().getHeight() / 64);

        if(block.isInside(e.getBounds())) {
            e.setFallen(true);
            return false;
        }
        else if((nextXt == yt + 1) || (nextXt == xt + 1) || (nextYt == yt - 1) || (nextXt == xt - 1)) {
            if(TileMapObj.getBlocks()[nextXt + (nextYt * TileMapObj.height)] instanceof HoleBlock){
                Block nextblock = TileMapObj.getBlocks()[nextXt + (nextYt * TileMapObj.height)];

                if(e.getPos().getX() + e.getBounds().getXOffset() > block.getPos().getX() 
                && e.getPos().getY() + e.getBounds().getYOffset() > block.getPos().getY()
                && nextblock.getWidth() + nextblock.getPos().getX() > e.getBounds().getWidth() + (e.getPos().getX() + e.getBounds().getXOffset())
                && nextblock.getHeight() + nextblock.getPos().getY() > e.getBounds().getHeight() + (e.getPos().getY() + e.getBounds().getYOffset())) {
                    e.setFallen(true);
                }
                return false;
            }
        }
        e.setFallen(false);
        return false;
    }
}

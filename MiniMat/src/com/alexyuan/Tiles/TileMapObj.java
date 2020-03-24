package com.alexyuan.Tiles;

import java.awt.Graphics2D;
import java.util.HashMap;

import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.Tiles.blocks.Block;
import com.alexyuan.Tiles.blocks.HoleBlock;
import com.alexyuan.Tiles.blocks.ObjBlock;

public class TileMapObj extends TileMap {

	private static HashMap<String, Block> two_blocks;

    private int tileWidth, tileHeight, width, height;
	
	public TileMapObj(String data, SpriteSheet sprite, int width, int height, int tileWidth, int tileHeight,
			int tileColumns) {
		
		this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.width = width;
        this.height = height;	
        
		Block tempBlock;
        two_blocks = new HashMap<String, Block>();

        String[] block = data.split(",");
        for(int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
             if(temp != 0) {
                 if(temp == 172) {
                     // TODO: find edge and connect them to form one polygon
                     tempBlock = new HoleBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns) ), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                 } else {
                     tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns) ), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                 }
                 two_blocks.put(String.valueOf(((int) (i % width)) + "," + String.valueOf((int) (i / height) )), tempBlock);
             }
        }
	}

	@Override
	public void render(Graphics2D g) {
		
		for(Block block : two_blocks.values()) {
			block.render(g);
		}
	}
	
	public static HashMap<String,Block> getTwoBlock() {
		return two_blocks;
	}

}

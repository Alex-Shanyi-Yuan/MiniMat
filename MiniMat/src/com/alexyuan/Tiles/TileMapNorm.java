package com.alexyuan.Tiles;

import java.awt.Graphics2D;

import com.alexyuan.LoadFile.SpriteSheet;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.Tiles.blocks.Block;
import com.alexyuan.Tiles.blocks.NormBlock;

public class TileMapNorm extends TileMap {

	private Block[] blocks;

    private int tileWidth;
    private int tileHeight;

    private int height;
	
	public TileMapNorm(String data, SpriteSheet sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
		blocks = new Block[width * height];

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.height = height;

        String[] block = data.split(",");

        for(int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            if(temp != 0) {
                blocks[i] = new NormBlock(sprite.getNewSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns) ), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                //blocks[i].setMaterial(0);
            }
        }
	}

	@Override
	public void render(Graphics2D g) {
		for(Block block : blocks) {
			if(block != null)block.render(g);
		}
	}

}

package com.alexyuan.LoadFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Textures {
	
	private static BufferedImage icon = null;
	private static BufferedImage menuBack = null;
	private static BufferedImage buttonBoard = null;
	private static SpriteSheet player = null;
	private static SpriteSheet girl = null;
	private static SpriteSheet heart = null;
	private static SpriteSheet greenG = null;
	private static SpriteSheet purpleG = null;
	
	public Textures() {
		icon = loadImage("/resources/Texture/Icon.png");
		menuBack = loadImage("/resources/Texture/MenuBack.jpg");
		buttonBoard = loadImage("/resources/Texture/ButtonBoard.png");
		player = new SpriteSheet("resources/Entity/wizardPlayer.png", 64, 64);
		girl = new SpriteSheet("resources/Entity/littlegirl.png", 48, 48);
		heart = new SpriteSheet("resources/Texture/health_player.png", 17, 17);
		greenG = new SpriteSheet("resources/Entity/green_goblin.png",32, 32);
		purpleG = new SpriteSheet("resources/Entity/purple_goblin.png",32, 32);
	}
	
	private BufferedImage loadImage(String path){
		try {
			return ImageIO.read(Textures.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage getIcon() {
		return icon;
	}
	
	public static BufferedImage getMenuBack() {
		return menuBack;
	}
	
	public static BufferedImage getButtonBoard() {
		return buttonBoard;
	}
	
	public static SpriteSheet getPlayer() {
		return player;
	}
	
	public static SpriteSheet getGirl() {
		return girl;
	}
	
	public static SpriteSheet getHeart() {
		return heart;
	}
	
	public static SpriteSheet getGreenGoblin() {
		return greenG;
	}

	public static SpriteSheet getPurpleGoblin() {
		return purpleG;
	}
}

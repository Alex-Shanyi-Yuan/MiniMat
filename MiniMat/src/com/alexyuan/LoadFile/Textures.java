package com.alexyuan.LoadFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Textures {
	
	private static BufferedImage icon = null;
	private static BufferedImage menuBack = null;
	private static BufferedImage board = null;
	private static BufferedImage storyBack = null;
	
	private static SpriteSheet player = null;
	private static SpriteSheet girl = null;
	private static SpriteSheet heart = null;
	private static SpriteSheet greenG = null;
	private static SpriteSheet purpleG = null;
	private static SpriteSheet minotaur = null;
	private static SpriteSheet tinyMon = null;
	private static SpriteSheet chest = null;
	private static SpriteSheet potionH = null;
	private static SpriteSheet potionD = null;
	private static SpriteSheet photo = null;
	
	public Textures() {
		icon = loadImage("/resources/Texture/Icon.png");
		menuBack = loadImage("/resources/Texture/MenuBack.jpg");
		storyBack = loadImage("/resources/Texture/StoryBack.jpg");
		board = loadImage("/resources/Texture/Board.png");
		
		player = new SpriteSheet("resources/Entity/wizardPlayer.png", 64, 64);
		girl = new SpriteSheet("resources/Entity/littlegirl.png", 48, 48);
		heart = new SpriteSheet("resources/Texture/health_player.png", 17, 17);
		greenG = new SpriteSheet("resources/Entity/green_goblin.png",32, 32);
		purpleG = new SpriteSheet("resources/Entity/purple_goblin.png",32, 32);
		minotaur = new SpriteSheet("resources/Entity/Minotaur.png",96, 96);
		tinyMon = new SpriteSheet("resources/Entity/TinyMon.png", 32, 32);
		
		chest = new SpriteSheet("resources/Entity/Chest.png", 64, 64);
		potionH = new SpriteSheet("resources/Entity/Health_Potion.png", 64, 64);
		potionD = new SpriteSheet("resources/Entity/Damage_Potion.png", 64, 64);
		photo = new SpriteSheet("resources/Entity/photo.png", 1200, 706);
		
		
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
	
	public static BufferedImage getStoryBack() {
		return storyBack;
	}
	
	public static BufferedImage getBoard() {
		return board;
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
	
	public static SpriteSheet getMinotaur() {
		return minotaur;
	}
	
	public static SpriteSheet getMon() {
		return tinyMon;
	}
	
	public static SpriteSheet getChest() {
		return chest;
	}
	
	public static SpriteSheet getPotionH() {
		return potionH;
	}
	
	public static SpriteSheet getPotionD() {
		return potionD;
	}

	public static SpriteSheet getPhoto() {
		return photo;
	}
}

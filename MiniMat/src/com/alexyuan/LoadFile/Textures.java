package com.alexyuan.LoadFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Textures {
	
	private static BufferedImage icon = null;
	private static BufferedImage menuBack = null;
	private static BufferedImage buttonBoard = null;
	
	public Textures() {
		icon = loadImage("/resources/Texture/Icon.png");
		menuBack = loadImage("/resources/Texture/MenuBack.jpg");
		buttonBoard = loadImage("/resources/Texture/ButtonBoard.png");
	}
	
	private BufferedImage loadImage(String path){
		try {
			return ImageIO.read(Textures.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
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
}

package com.alexyuan.Graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Fonts {
	
	private Font title, subTitle;
	
	public Fonts() {
		loadFont(title, "/resources/Font/mago1.ttf", 29);
	}
	
	private void loadFont(Font f, String path, int size) {
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getClassLoader().getResource(path).toString())).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(f);
		}catch(IOException | FontFormatException e) {
			e.printStackTrace();
			System.out.println("Can't find File");
		}
	}
}
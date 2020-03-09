package com.alexyuan.LoadFile;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Fonts {
	
	private Font magon1, magon2, magon3;
	
	public Fonts() {
		magon1 = loadFont("resources/Font/mago1.ttf");
		magon2 = loadFont("resources/Font/mago2.ttf");
		magon3 = loadFont("resources/Font/mago3.ttf");
	}
	
	private Font loadFont(String path) {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(Fonts.class.getProtectionDomain().getCodeSource().getLocation().getPath()+path));

	        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
	        
	        return font;
		}catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Font getMagon1() {
		return magon1;
	};
	
	public Font getMagon2() {
		return magon2;
	};
	
	public Font getMagon3() {
		return magon3;
	};
}
package com.alexyuan.LoadFile;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Textures {
	
	private ImageIcon icon = null;
	
	public Textures() {
		icon = loadTextures("/resources/Texture/Icon.png");
	}
	
	private ImageIcon loadTextures(String path) {
		java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
}

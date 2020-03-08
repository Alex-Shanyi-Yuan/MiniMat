package com.alexyuan.Graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Sprite {

    public BufferedImage image;

	private int[] pixels;
	private int[] ogpixels;

	private int w;
	private int h;	

	public static enum effect {NORMAL, SEPIA, REDISH, GRAYSCALE, NEGATIVE, DECAY};

	private float[][] id = {{1.0f, 0.0f, 0.0f},
							{0.0f, 1.0f, 0.0f},
							{0.0f, 0.0f, 1.0f},
							{0.0f, 0.0f, 0.0f}};

	private float[][] negative = {{1.0f, 0.0f, 0.0f},
								  {0.0f, 1.0f, 0.0f},
								  {0.0f, 0.0f, 1.0f},
								  {0.0f, 0.0f, 0.0f}};

	private float[][] decay = {{0.000f, 0.333f, 0.333f},
							   {0.333f, 0.000f, 0.333f},
							   {0.333f, 0.333f, 0.000f},
							   {0.000f, 0.000f, 0.000f}};

	private float[][] sepia = {{0.393f, 0.349f, 0.272f},
							   {0.769f, 0.686f, 0.534f},
							   {0.189f, 0.168f, 0.131f},
							   {0.000f, 0.000f, 0.000f}};

	private float[][] redish = {{1.0f, 0.0f, 0.0f},
							    {0.0f, 0.3f, 0.0f},
								{0.0f, 0.0f, 0.3f},
							    {0.0f, 0.0f, 0.0f}};
								
	private float[][] grayscale = {{0.333f, 0.333f, 0.333f},
							  	   {0.333f, 0.333f, 0.333f},
								   {0.333f, 0.333f, 0.333f},
								   {0.000f, 0.000f, 0.000f}};

	private float[][] currentEffect = id;

    public Sprite(BufferedImage image) {
		this.image = image;
		this.w = image.getWidth();
		this.h = image.getHeight();
		ogpixels = image.getRGB(0, 0, w, h, ogpixels, 0, w);
		pixels = ogpixels;
	}
	
	public int getWidth() { return w; }
	public int getHeight() { return h; }

    public void saveColors() {
		pixels = image.getRGB(0, 0, w, h, pixels, 0, w);
		currentEffect = id;
	}
	
	public void restoreColors() {
		image.setRGB(0, 0, w, h, pixels, 0, w);
	}

	public void restoreDefault() {
		image.setRGB(0, 0, w, h, ogpixels, 0, w);
	}

	// in #FFFFFF format
	public Color hexToColor(String color) {
		return new Color(
            Integer.valueOf(color.substring(1, 3), 16),
            Integer.valueOf(color.substring(3, 5), 16),
            Integer.valueOf(color.substring(5, 7), 16));
	}

    
    public Sprite getSubimage(int x, int y, int w, int h) {
        return new Sprite(image.getSubimage(x, y, w, h));
	}
	
	public Sprite getNewSubimage(int x, int y, int w, int h) {
		BufferedImage temp = image.getSubimage(x, y, w, h);
		BufferedImage newImage = new BufferedImage(image.getColorModel(), image.getRaster().createCompatibleWritableRaster(w,h), image.isAlphaPremultiplied(), null);
		temp.copyData(newImage.getRaster());
        return new Sprite(newImage);
	}

	public Sprite getNewSubimage() {
		return getNewSubimage(0, 0, this.w, this.h);
	}
}
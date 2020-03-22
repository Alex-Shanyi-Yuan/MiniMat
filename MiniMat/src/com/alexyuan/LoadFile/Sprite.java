package com.alexyuan.LoadFile;

import java.awt.image.BufferedImage;

public class Sprite {

    private BufferedImage image;
    
	private int w;
	private int h;		

    public Sprite(BufferedImage image) {
		this.image = image;
		this.w = image.getWidth();
		this.h = image.getHeight();
	}
	
	public int getWidth() { return w; }
	public int getHeight() { return h; }
	public BufferedImage getImage() { return image; }
    
    public Sprite getSubimage(int x, int y, int w, int h) {
        return new Sprite(image.getSubimage(x, y, w, h));
	}
	
	public Sprite getNewSubimage(int x, int y, int w, int h) {
		BufferedImage temp = image.getSubimage(x, y, w, h);
		BufferedImage newImage = new BufferedImage(image.getColorModel(), image.getRaster().createCompatibleWritableRaster(w,h), image.isAlphaPremultiplied(), null);
		temp.copyData(newImage.getRaster());
        return new Sprite(newImage);
	}
}
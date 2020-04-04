package com.alexyuan.LoadFile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.alexyuan.Math.Vector2f;
 
public class SpriteSheet {

    private Sprite SPRITESHEET = null;
    private Sprite[][] spriteArray;
    private final int TILE_SIZE = 64;
    private int w;
    private int h;
    private int wSprite;
    private int hSprite;
    private String file;

    public SpriteSheet(String file) {
        this.file = file;
        w = TILE_SIZE;
        h = TILE_SIZE;

        SPRITESHEET = new Sprite(loadSprite(file));

        wSprite = Math.round(SPRITESHEET.getImage().getWidth() / w);
        hSprite = Math.round(SPRITESHEET.getImage().getHeight() / h);
        loadSpriteArray();
    }

    public SpriteSheet(Sprite sprite, String name, int w, int h) {
        this.w = w;
        this.h = h;

        SPRITESHEET = sprite;

        wSprite = Math.round(SPRITESHEET.getImage().getWidth() / w);
        hSprite = Math.round(SPRITESHEET.getImage().getHeight() / h);
        loadSpriteArray();
        
    }

    public SpriteSheet(String file, int w, int h) {
        this.w = w;
        this.h = h;
        this.file = file;

        SPRITESHEET = new Sprite(loadSprite(file));

        wSprite = SPRITESHEET.getImage().getWidth() / w;
        hSprite = SPRITESHEET.getImage().getHeight() / h;
        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wSprite = SPRITESHEET.getImage().getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hSprite = SPRITESHEET.getImage().getHeight() / h;
    }

    public int getWidth() { return w; }
    public int getHeight() { return h; }
    public int getRows() { return hSprite; }
    public int getCols() { return wSprite; }
    public int getTotalTiles() { return wSprite * hSprite; }
    public String getFilename() { return file; }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new Sprite[hSprite][wSprite];

        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public Sprite getSpriteSheet() {
        return SPRITESHEET;
    }

    public Sprite getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    public Sprite getNewSprite(int x, int y) {
        return SPRITESHEET.getNewSubimage(x * w, y * h, w, h);
    }

    public Sprite getSprite(int x, int y, int w, int h) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getSubimage(int x, int y, int w, int h) {
        return SPRITESHEET.getImage().getSubimage(x, y, w, h);
    }

    public Sprite[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public Sprite[][] getSpriteArray2() {
        return spriteArray;
    }

    public static void drawArray(Graphics2D g, ArrayList<Sprite> img, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.getX();
        float y = pos.getY();

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i).getImage(), (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }

}
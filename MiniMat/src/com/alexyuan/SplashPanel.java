package com.alexyuan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;
import com.alexyuan.LoadFile.Audio;

public class SplashPanel extends JPanel implements Runnable{
	
	private static Fonts fonts;
	private static Audio audio;
	private static Textures texture;
	
	private int width, height;
	private boolean loading = false;
	
	private BufferedImage img;
	private Graphics2D g;
	
	private Thread thread;
	
	private int widthRect = 0;

	public SplashPanel(int width, int height) {
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width,height));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		
		loading = true;
		
		thread = new Thread(this, "Splash");
		thread.start();
		
		fonts = new Fonts();
		audio = new Audio();
		texture = new Textures();
		
		img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();	
		
	}
	
	private void stop() {
		
		GameLauncher.getWindow().setVisible(true);
		GameWindow.getGamePanel().setVisible(true);
		
		GameLauncher.getSplashWindow().setVisible(false);
		GameLauncher.getSplashWindow().dispose();
	}
	
	private void render() {
		if(g != null) {
			g.setColor(new Color(243,115,0));
			g.fillRect(0, 0, width, height);
			
			g.setColor(Color.BLACK);
			g.drawRect(5, 470, 390, 25);
			
			g.fillRect(10, 475, widthRect, 15);
			
			g.setColor(Color.WHITE);
			g.setFont(fonts.getMagon3().deriveFont(100f));
			g.drawString("MiniMat" , 100, 60);
			g.setFont(fonts.getMagon1().deriveFont(60f));
			g.drawString("-Dungeon-", 140, 100);
		}
	}
	
	private void draw() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(img, 0,0,width,height, null);
		g2.dispose();
	}
	
	private void update() {
		try {
			if(widthRect < 380) {
				Thread.sleep(100);
				widthRect += 20;
			}else
				loading = false;
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while(loading) {
			update();
			draw();
			render();
		}
		
		stop();
		
	}

	public static Fonts getFonts() {
		return fonts;
	}

	public static Audio getAudio() {
		return audio;
	}

	public static Textures getTexture() {
		return texture;
	}

}

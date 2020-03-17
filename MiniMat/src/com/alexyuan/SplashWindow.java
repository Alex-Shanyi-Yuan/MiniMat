package com.alexyuan;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.alexyuan.LoadFile.Audio;
import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;

public class SplashWindow extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;

	private Canvas c;
	
	private boolean loading = false, notDone = true;
	private int width, height, widthRect = 0;
	
	private Thread thread;
	
	SplashWindow(int width, int height) {
		this.width = width;
		this.height = height;
		
		c = new Canvas();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setUndecorated(true);
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		add(c);
		setVisible(true);
		c.setVisible(true);
		
		thread = new Thread(this, "Loading");
		thread.start();
		
		start();		
	}
	
	private void start() {
		loading = true;
		
		new Fonts();
		
		while(loading || notDone) {
			render();
			update();
		}
		
		setVisible(false);
		dispose();
	}
	
	private void update() {
		try {
			if(widthRect < 380) {
				Thread.sleep(250);
				widthRect += 20;
			}else
				loading = false;
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void render() {
		BufferStrategy bs = c.getBufferStrategy();
		if(bs == null) {
			c.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if(g != null) {
			g.setColor(new Color(243,115,0));
			g.fillRect(0, 0, width, height);
			
			g.setColor(Color.BLACK);
			g.drawRect(5, 470, 390, 25);
			
			g.fillRect(10, 475, widthRect, 15);
			
			g.setColor(Color.WHITE);
			g.setFont(Fonts.getMagon3().deriveFont(100f));
			g.drawString("MiniMat" , 100, 60);
			g.setFont(Fonts.getMagon1().deriveFont(60f));
			g.drawString("-Dungeon-", 140, 100);
		}
		
		bs.show();
		g.dispose();
		
	}

	@Override
	public void run() {
		new Audio();
		new Textures();
		
		notDone = false;
	}
	
}

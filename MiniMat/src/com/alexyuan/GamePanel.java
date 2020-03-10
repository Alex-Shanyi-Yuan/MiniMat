package com.alexyuan;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.alexyuan.States.GameStateManager;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;
import com.alexyuan.GameLauncher;

public class GamePanel extends Canvas implements Runnable{

	private int width, height;
	
	private boolean running = false;
	
	private MouseHandler mouse;
	private KeyHandler key;

	private GameStateManager gsm;
	
	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void addNotify() {
		super.addNotify();
		
		Thread thread = new Thread(this, "Game");
		thread.start();
		
	}

    public void run() {
         init();
        
         long lastTime = System.nanoTime();
         double amountTicks = 60.0;
         int ticks = 0;
         double ns = 1000000000/amountTicks;
         double deltaTime = 0;
         long timer = System.currentTimeMillis();
         int frames = 0;
 
         while (running) {
         	long now = System.nanoTime();
         	deltaTime += (now - lastTime) / ns;
         	lastTime = now;
        	
        	if(deltaTime >= 1) {
        		deltaTime --;
        		update();
        		ticks++;
        	}
        	
        	frames++;
            input(mouse, key);
            render();
            
            if(System.currentTimeMillis() - timer > 1000) {
            	timer += 1000;
            	GameLauncher.getWindow().setTitle("Mini Mat         							  FPS: " + frames + " | GameUpdate: " + ticks);
            	ticks = 0;
            	frames = 0;
            }
        }
    }

	private void init() {
		running = true;
		
		mouse = new MouseHandler(this);
		key = new KeyHandler(this);
		
		gsm = new GameStateManager();
	}
	
	private void update() {
		gsm.update();
	}
	
	private void input(MouseHandler mouse, KeyHandler key) {
		gsm.input(mouse, key);
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		
		g.setColor(new Color(66,134,244));
		g.fillRect(0,0, width, height);
			
		gsm.render(g);
		
		bs.show();
		g.dispose();
	}	
}

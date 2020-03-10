package com.alexyuan;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.alexyuan.LoadFile.Textures;

public class GameWindow extends JFrame {
	
	private final String TITLE = "MINI MAT";
	
	private static GamePanel panel;
	
	private int width = 1200, height = 600;
	
	public GameWindow() {
		
		panel = new GamePanel(width, height);
		
		setSize(width, height);
		setTitle(TITLE);
		setIconImage(SplashPanel.getTexture().getIcon().getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		add(panel);
		setVisible(false);
		panel.setVisible(false);
	}
	
	public static GamePanel getGamePanel() {
		return panel;
	}
}

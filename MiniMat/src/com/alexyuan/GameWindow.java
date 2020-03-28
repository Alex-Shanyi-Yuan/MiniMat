package com.alexyuan;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.alexyuan.LoadFile.Textures;

import static com.alexyuan.GameLauncher.*;

public class GameWindow extends JFrame{
	
	private final String TITLE = "MINI MAT";
	
	private static GamePanel panel;
	
	private int width, height;
	
	public GameWindow(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		panel = new GamePanel(1200, 750);
		
		setSize(width, height);
		setTitle(TITLE);
		setIconImage(Textures.getIcon());
		setResizable(false);
		setIgnoreRepaint(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		add(panel);
		setVisible(true);
		panel.setVisible(true);
	}
	
	public static GamePanel getGamePanel() {
		return panel;
	}
	
}

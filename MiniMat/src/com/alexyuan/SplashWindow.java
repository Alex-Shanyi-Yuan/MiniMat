package com.alexyuan;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SplashWindow extends JFrame{
	private static GameWindow window;
	
	SplashWindow() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new SplashPanel(400, 500));
		pack();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
		setVisible(true);
		
	}

	
	public static GameWindow getWindow() {
		return window;
	}
	
	public void initGameWindow() {
		window = new GameWindow();
	}

}

package com.alexyuan;

import java.awt.Window;

import javax.swing.JComponent;

public class GameLauncher {
	
	private static GameWindow window;
	private static SplashWindow splash;
	
	public GameLauncher() {
		splash = new SplashWindow();
		window = new GameWindow();
	}
	
	public static void main(String[] args) {
		new GameLauncher();
	}

	public static GameWindow getWindow() {
		return window;
	}

	public static SplashWindow getSplashWindow() {
		return splash;
	}
}

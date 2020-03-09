package com.alexyuan;

import java.awt.Window;

public class GameLauncher {
	
	private SplashWindow splashWindow;
	
	public GameLauncher() {
		splashWindow = new SplashWindow();
	}
	
	public static void main(String[] args) {
		new GameLauncher();
	}

	public SplashWindow getSplashWindow() {
		return splashWindow;
	}
}

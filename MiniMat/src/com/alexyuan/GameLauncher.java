package com.alexyuan;

public class GameLauncher {
	
	private static GameWindow window;
	private static SplashWindow splash;
	
	public GameLauncher() {
		splash = new SplashWindow(400,500);
		window = new GameWindow(1200,750);
		
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

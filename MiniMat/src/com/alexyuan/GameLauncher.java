package com.alexyuan;

import com.alexyuan.LoadFile.Audio;
import com.alexyuan.LoadFile.Fonts;
import com.alexyuan.LoadFile.Textures;

public class GameLauncher {
	
	private static GameWindow window;
	private static SplashWindow splash;
	
	public GameLauncher() {
		
		new Fonts();
		new Audio();
		new Textures();
//		splash = new SplashWindow(400,500);
		window = new GameWindow(1200,700);
		
	}
	
	public static void main(String[] args) {
		new GameLauncher();
	}

	public static GameWindow getWindow() {
		return window;
	}

}

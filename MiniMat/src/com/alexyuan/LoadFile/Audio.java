package com.alexyuan.LoadFile;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.alexyuan.States.GameStateManager;

public class Audio {
	
	private Clip[] backgrounds;
	
	public Audio() {
		backgrounds = new Clip[GameStateManager.STATES];
		backgrounds[GameStateManager.MENU] = loadFile(Audio.class.getResource("/resources/Audio/IntroMusic.wav"));
		backgrounds[GameStateManager.PLAY] = loadFile(Audio.class.getResource("/resources/Audio/PlaySceneBack.wav"));
	}
	
	private static Clip loadFile(URL musicLocation) {
		
		try {

            //Require Audio format
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicLocation);

            Clip background = AudioSystem.getClip();
            background.open(audioInput);
            
            return background;
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException ix){ix.printStackTrace();}
		
		return null;
	}
	
	public Clip[] getBackgrouds() {
		return backgrounds;
	}
	
    //music.loop(Clip.LOOP_CONTINUOUSLY);
 
  	//effect.start();    
 
}

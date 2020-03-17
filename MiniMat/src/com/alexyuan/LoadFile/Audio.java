package com.alexyuan.LoadFile;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	
	private static Clip intro;
	
	public Audio() {
		intro = loadFile(Audio.class.getResource("/resources/Audio/IntroMusic.wav"));
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
	
	public static Clip getIntro() {
		return intro;
	}
	
    //music.loop(Clip.LOOP_CONTINUOUSLY);
 
  	//effect.start();    
 
}

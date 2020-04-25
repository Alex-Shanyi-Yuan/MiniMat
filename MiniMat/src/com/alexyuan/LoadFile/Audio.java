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
	
	public final byte SWORDATTACK = 0, HURT = 1, GAMEOVER = 2, WINGAME = 3, PLAYERDIE = 4;
	
	private Clip[] backgrounds;
	private Clip[] effects;
	
	public Audio() {
		backgrounds = new Clip[GameStateManager.STATES];
		backgrounds[GameStateManager.MENU] = loadFile(Audio.class.getResource("/resources/Audio/IntroMusic.wav"));
		backgrounds[GameStateManager.PLAY] = loadFile(Audio.class.getResource("/resources/Audio/PlaySceneBack.wav"));
		
		effects = new Clip[5];
		effects[SWORDATTACK] = loadFile(Audio.class.getResource("/resources/Audio/SwordAttack.wav"));
		effects[HURT] = loadFile(Audio.class.getResource("/resources/Audio/Hurt.wav"));	
		effects[GAMEOVER] = loadFile(Audio.class.getResource("/resources/Audio/LoseGame.wav"));	
		effects[WINGAME] = loadFile(Audio.class.getResource("/resources/Audio/WinGame.wav"));
		effects[PLAYERDIE] = loadFile(Audio.class.getResource("/resources/Audio/DeathPlayer.wav"));
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
	
	public Clip[] getEffects() {
		return effects;
	}
	
    //music.loop(Clip.LOOP_CONTINUOUSLY);
 
  	//effect.start();    
 
}

package com.alexyuan.States;

import java.awt.Graphics2D;

import javax.sound.sampled.Clip;

import com.alexyuan.GamePanel;
import com.alexyuan.LoadFile.Audio;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class GameStateManager {
	
	private GameState[] states;
	
	public static final int STATES = 5;
	public static final int MENU = 2;
	public static final int PLAY = 0;
	public static final int PAUSE = 1;
	public static final int STORY = 3;
	public static final int GAMEOVER = 4;
	
	private int width, height;
    
	private Audio audio;
	
	public GameStateManager(int width, int height, Audio audio) {
		this.width = width;
		this.height = height;
		this.audio = audio;
		
		states = new GameState[STATES];
		
		add(MENU);
	}
	
	public void pop(int state) {
        states[state] = null;
        
        if(audio.getBackgrouds()[state] != null)
        	audio.getBackgrouds()[state].stop();
    }

	public void add(int state) {
        if (states[state] != null)
            return;

        if (state == PLAY) 
            states[PLAY] = new PlayState(this);
        else if (state == MENU) 
            states[MENU] = new MenuState(this);
        else if (state == PAUSE) 
            states[PAUSE] = new PauseState(this);
        else if (state == STORY) 
            states[STORY] = new StoryState(this);
        else if (state == GAMEOVER) 
            states[GAMEOVER] = new GameOverState(this);
        
        if(audio.getBackgrouds()[state] != null) {
        	audio.getBackgrouds()[state].setMicrosecondPosition(0);
        	audio.getBackgrouds()[state].start();
        	audio.getBackgrouds()[state].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
	
	public boolean isStateActive(int state) {
        return states[state] != null;
	}

	public void addAndpop(int state, int remove) {
        pop(remove);
        add(state);
    }

	public void update(double time) {
		for(GameState i : states) {
			if(i != null)
				i.updata(time);
		}
	}
	
	public void render(Graphics2D g) {
		for(GameState i : states) {
			if(i != null)
				i.render(g);
		}
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		for(GameState i : states) {
			if( i != null)
				i.input(mouse, key);
		}
	}
	
	public Audio getAudio() {
		return audio;
	}
	
}

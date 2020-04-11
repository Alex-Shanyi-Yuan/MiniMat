package com.alexyuan.States;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.GamePanel;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class GameStateManager {
	
	private GameState[] states;
	
	public final int MENU = 0;
	public final int PLAY = 1;
	public final int PAUSE = 2;
	public final int GAMEOVER = 3;
	public final int SETTING = 4;
	private int width, height;
    
	private static Vector2f map;
	
	public GameStateManager(int width, int height) {
		this.width = width;
		this.height = height;
		
		map = new Vector2f(GamePanel.WIDTH, GamePanel.HEIGHT);
		Vector2f.setWorldVar(map.getX(), map.getY());
		
		states = new GameState[5];
		
		states[MENU] = new MenuState(this);
	}
	
	public void pop(int state) {
        states[state] = null;
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
        else if (state == GAMEOVER) 
            states[GAMEOVER] = new GameOverState(this);
        else if (state == SETTING)
        	states[SETTING] = new SettingState(this);
    
    }
	
	protected boolean isStateActive(int state) {
        return states[state] != null;
    }

	protected void addAndpop(int state) {
        addAndpop(state, MENU);
    }

	protected void addAndpop(int state, int remove) {
        pop(remove);
        add(state);
    }

	public void update(double time) {
		Vector2f.setWorldVar(map.getX(), map.getY());
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
	
}

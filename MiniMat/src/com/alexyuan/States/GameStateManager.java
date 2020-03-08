package com.alexyuan.States;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.alexyuan.GamePanel;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.util.KeyHandler;
import com.alexyuan.util.MouseHandler;

public class GameStateManager {
	
	private GameState[] states;
	
	public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
	
	private static Vector2f map;
	
	public GameStateManager() {
		map = new Vector2f(GamePanel.WIDTH, GamePanel.HEIGHT);
		Vector2f.setWorldVar(map.getX(), map.getY());
		
		states = new GameState[4];
		
		states[PLAY] = new PlayState(this);
	}
	
	public void pop(int state) {
        states[state] = null;
    }

    public void add(int state) {
        if (states[state] != null)
            return;

        if (state == PLAY) {
            states[PLAY] = new PlayState(this);
        }
        else if (state == MENU) {
            states[MENU] = new MenuState(this);
        }
        else if (state == PAUSE) {
            states[PAUSE] = new PauseState(this);
        }
        else if (state == GAMEOVER) {
            states[GAMEOVER] = new GameOverState(this);
        }
    
    }

    public void addAndpop(int state) {
        addAndpop(state, 0);
    }

    public void addAndpop(int state, int remove) {
        pop(state);
        add(state);
    }

	public void update() {
		Vector2f.setWorldVar(map.getX(), map.getY());
		for(GameState i : states) {
			if(i != null)
				i.updata();
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

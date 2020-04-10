package com.alexyuan.util;

import com.alexyuan.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;

public class KeyHandler implements KeyListener{

    public static List<Key> keys = new ArrayList<Key>();

    public class Key {
        private int presses, absorbs;
        private boolean holdDown, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if(pressed != holdDown) {
                holdDown = pressed;
            }
            if(pressed) {
                presses++;
            }
        }

        public void tick() {
            if(absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }

		public int getPresses() {
			return presses;
		}

		public int getAbsorbs() {
			return absorbs;
		}

		public boolean isHoldDown() {
			return holdDown;
		}

		public boolean isClicked() {
			return clicked;
		}
        
        
    }

    private Key up = new Key();
    private Key down = new Key();
    private Key left = new Key();
    private Key right = new Key();

    private Key enter = new Key();
    private Key escape = new Key();
    private Key shift = new Key();
    private Key f = new Key();
    private Key attack = new Key();

    private Key one = new Key();
    private Key two = new Key();
    
    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }

    public void releaseAll() {
        for(Key i : keys) {
            i.holdDown = false;
        }
    }

    public void tick() {
        for(Key i : keys) {
            i.tick();
        }
    }

    public void toggle(KeyEvent e, boolean pressed) {
        if(e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_F) f.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) shift.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_1) one.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_2) two.toggle(pressed);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }

	public static List<Key> getKeys() {
		return keys;
	}

	public Key getUp() {
		return up;
	}

	public Key getDown() {
		return down;
	}

	public Key getLeft() {
		return left;
	}

	public Key getRight() {
		return right;
	}

	public Key getEnter() {
		return enter;
	}

	public Key getEscape() {
		return escape;
	}

	public Key getShift() {
		return shift;
	}

	public Key getF() {
		return f;
	}
	
	public Key getAttack() {
		return attack;
	}
	
	public Key getOne() {
		return one;
	}
	
	public Key getTwo() {
		return two;
	}
    
}
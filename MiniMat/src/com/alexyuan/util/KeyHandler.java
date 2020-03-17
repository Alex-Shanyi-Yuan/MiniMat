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
    
    private Key upArrow = new Key();
    private Key downArrow = new Key();
    private Key leftArrow = new Key();
    private Key rightArrow = new Key();
    
    private Key inventory = new Key();
    private Key enter = new Key();
    private Key escape = new Key();
    private Key shift = new Key();
    private Key f1 = new Key();

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
        if(e.getKeyCode() == KeyEvent.VK_UP) upArrow.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_DOWN) downArrow.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_LEFT) leftArrow.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) rightArrow.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_E) inventory.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_F1) f1.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) shift.toggle(pressed);
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

	public Key getInventory() {
		return inventory;
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

	public Key getF1() {
		return f1;
	}

	public Key getUpArrow() {
		return upArrow;
	}

	public Key getDownArrow() {
		return downArrow;
	}

	public Key getLeftArrow() {
		return leftArrow;
	}

	public Key getRightArrow() {
		return rightArrow;
	}
    
}
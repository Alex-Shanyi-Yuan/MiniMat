package com.alexyuan.util;

import java.awt.Color;
import java.awt.Graphics;

import com.alexyuan.GamePanel;
import com.alexyuan.Entity.Cretures.Creture;
import com.alexyuan.Math.AABB;
import com.alexyuan.Math.Vector2f;
import com.alexyuan.States.PlayState;

public class Camera {

    private AABB collisionCam;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private float dx;
    private float dy;
    private float maxSpeed = 8f;
    private float acc = 3f;
    private float deacc = 0.3f;

    private int widthLimit;
    private int heightLimit;

    private int tileSize = 64;

    private Creture c;

    public Camera(AABB collisionCam) {
        this.collisionCam = collisionCam;
    }

    public void setLimit(int widthLimit, int heightLimit) {
        this.widthLimit = widthLimit;
        this.heightLimit = heightLimit;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public Creture getTarget() { return c; }

    public Vector2f getPos() {
        return collisionCam.getPos();
    }
    public AABB getBounds() {
        return collisionCam;
    }

    public void update() {
        move();
        if(c != null) {
            if (!c.getXCol()) {
                if ((c.getBounds().getPos().getWorldVar().getX() + dx) < Vector2f.getWorldVarX(widthLimit - collisionCam.getWidth() / 2) + tileSize
                    && (c.getBounds().getPos().getWorldVar().getX() + c.getDx()) > Vector2f.getWorldVarX(600 - tileSize/2 + 23)) {
                    PlayState.getMap().addX(dx);
                    collisionCam.getPos().addX(dx);
                    //bounds.getPos().x += dx;
                }
            }
            if (!c.getYCol()) {
                if ((c.getBounds().getPos().getWorldVar().getY() + dy) < Vector2f.getWorldVarY(heightLimit - collisionCam.getHeight() / 2) + tileSize
                    && (c.getBounds().getPos().getWorldVar().getY() + dy) > Vector2f.getWorldVarY(350 - tileSize/2 + 23)) {
                    PlayState.getMap().addY(dy);
                    collisionCam.getPos().addY(dy);
                //bounds.getPos().y += dy;
                }
            }
        } else {
            if(collisionCam.getPos().getX() + dx > 0
            && collisionCam.getPos().getWorldVar().getX() + dx < Vector2f.getWorldVarX(widthLimit - collisionCam.getWidth()) - tileSize) {
                PlayState.getMap().addX(dx);
                collisionCam.getPos().addX(dx);
            }

            if(collisionCam.getPos().getY() + dy > 0 && collisionCam.getPos().getWorldVar().getY() + dy < Vector2f.getWorldVarY(heightLimit - collisionCam.getHeight()) - tileSize) {
                PlayState.getMap().addY(dy);
                collisionCam.getPos().addY(dy);;
            }
        }
    }


    private void move() {
        if (up) {
            dy -= acc;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (down) {
            dy += acc;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (left) {
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (right) {
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    public void target(Creture c) {
        this.c = c;
        if(c != null) {
            acc = c.getAcc();
            deacc = c.getDeacc();
            maxSpeed = c.getMaxSpeed();
        } else {
            acc = 3;
            deacc = 0.3f;
            maxSpeed = 8;
        }
    }

    public void setMaxSpeed(float maxSpeed) {this.maxSpeed = maxSpeed; }
    public void setAcc(float acc) {this.acc = acc;}
    public void setDeacc(float deacc) {this.deacc = deacc;}
    
    public void updateCam(float acc, float maxSpeed, float deacc) {
    	setAcc(acc * 0.9f);
    	setDeacc(deacc * 0.9f);
    	setMaxSpeed(maxSpeed * 0.9f);
    }
    
    public void input(MouseHandler mouse, KeyHandler key) {

        if (c == null) {
            if (key.getUp().isHoldDown()) {
                up = true;
            } else {
                up = false;
            }
            if (key.getDown().isHoldDown()) {
                down = true;
            } else {
                down = false;
            }
            if (key.getLeft().isHoldDown()) {
                left = true;
            } else {
                left = false;
            }
            if (key.getRight().isHoldDown()) {
                right = true;
            } else {
                right = false;
            }
        } else {
            if (!c.getYCol()) {
                if (collisionCam.getPos().getY() + collisionCam.getHeight() / 2 + dy > c.getPos().getY() + c.getSize() + c.getDy() + 2) {
                    up = true;
                    down = false;
                } else if (collisionCam.getPos().getY() + collisionCam.getHeight() / 2 + dy < c.getPos().getY() + c.getSize() + c.getDy() - 2) {
                    down = true;
                    up = false;
                } else {
                    dy = 0;
                    up = false;
                    down = false;
                }
            }

            if (!c.getXCol()) {
                if (collisionCam.getPos().getX() + collisionCam.getWidth() / 2  + dx > c.getPos().getX() + c.getSize() + c.getDx() + 2) {
                    left = true;
                    right = false;
                } else if (collisionCam.getPos().getX() + collisionCam.getWidth() / 2 + dx < c.getPos().getX() + c.getSize() + c.getDx() - 2) {
                    right = true;
                    left = false;
                } else {
                    dx = 0;
                    right = false;
                    left = false;
                }
            }
        }
    }
}

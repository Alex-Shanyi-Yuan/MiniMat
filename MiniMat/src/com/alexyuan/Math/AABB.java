package com.alexyuan.Math;

import com.alexyuan.Tiles.TileMapObj;
import com.alexyuan.Tiles.blocks.Block;
import com.alexyuan.Tiles.blocks.HoleBlock;

public class AABB {

    private Vector2f pos;
    private float xOffset = 0;
    private float yOffset = 0;
    private float w;
    private float h;
    private float r;
    private int size;

    private float surfaceArea;

    public AABB(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;
        this.surfaceArea = w * h;

        size = Math.max(w, h);
    }

    public AABB(Vector2f pos, int r) {
        this.pos = pos;
        this.r = r;
        this.surfaceArea = (float) Math.PI * (r * r);

        size = r;
    }

    public Vector2f getPos() { return pos; }

    public float getRadius() { return r; }
    public float getSize() { return size; }
    public float getWidth() { return w; }
    public float getHeight() { return h; }
    public float getSurfaceArea() { return surfaceArea; }

    public void setBox(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public void setCircle(Vector2f pos, int r) {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public void setWidth(float f) { w = f; }
    public void setHeight(float f) { h = f; }

    public void setXOffset(float f) { xOffset = f; }
    public void setYOffset(float f) { yOffset = f; }
    public float getXOffset() { return xOffset; }
    public float getYOffset() { return yOffset; }

    public boolean collides(AABB bBox) {
        return collides(0, 0, bBox);
    }

    public boolean collides(float dx, float dy, AABB bBox) {
        float ax = ((pos.getX() + (xOffset)) + (this.w / 2)) + dx;
        float ay = ((pos.getY() + (yOffset)) + (this.h / 2)) + dy;
        float bx = ((bBox.getPos().getX() + (bBox.getXOffset())) + (bBox.getWidth() / 2));
        float by = ((bBox.getPos().getY() + (bBox.getYOffset())) + (bBox.getHeight() / 2));

        if (Math.abs(ax - bx) < (this.w / 2) + (bBox.getWidth() / 2)) {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.getHeight() / 2)) {
                return true;
            }
        }

        return false;
    }

    public boolean inside(int xp, int yp) {
        if(xp == -1 || yp == - 1) return false;

        int wTemp = (int) this.w;
        int hTemp = (int) this.h;
        int x = (int) this.pos.getX();
        int y = (int) this.pos.getY();

        if(xp < x || yp < y) {
            return false;
        }

        wTemp += x;
        hTemp += y;
        return ((wTemp < x || wTemp > xp) && (hTemp < y || hTemp > yp));
    }

    public boolean intersect(AABB aBox)
    {

        if((pos.getX() + xOffset > aBox.getPos().getX() + aBox.getXOffset() + aBox.getSize())
        || (aBox.getPos().getX() + xOffset > pos.getX() + aBox.getXOffset() + aBox.getSize()))
        {
            return false;
        }

        if((pos.getY() + yOffset > aBox.getPos().getY() + aBox.getYOffset() + aBox.getSize())
        || (aBox.getPos().getY() + yOffset > pos.getY() + aBox.getYOffset() + aBox.getSize()))
        {
            return false;
        }

        return true;
    }

    public boolean colCircle(AABB circle) {

        float totalRadius = r + circle.getRadius();
        totalRadius *= totalRadius;

        float dx = (pos.getX() + circle.getPos().getX());
        float dy = (pos.getY() + circle.getPos().getY());

        return totalRadius < (dx * dx) + (dy * dy);
    }

    public boolean colCircleBox(AABB aBox) {

        float dx = Math.max(aBox.getPos().getWorldVar().getX() + aBox.getXOffset(), Math.min(pos.getWorldVar().getX() + (r / 2), aBox.getPos().getWorldVar().getX() + aBox.getXOffset() + aBox.getWidth()));
        float dy = Math.max(aBox.getPos().getWorldVar().getY() + aBox.getYOffset(), Math.min(pos.getWorldVar().getY() + (r / 2), aBox.getPos().getWorldVar().getY() + aBox.getYOffset() + aBox.getHeight()));

        dx = pos.getWorldVar().getX() + (r / 2) - dx;
        dy = pos.getWorldVar().getY() + (r / 2) - dy;

        return Math.sqrt(dx * dx + dy * dy) < r / 2;
    }

    public float distance(Vector2f other) {
        float dx = pos.getX() - other.getX();
        float dy = pos.getY() - other.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public AABB merge(AABB other) {
        float minX = Math.min(pos.getX(), other.getPos().getX());
        float minY = Math.min(pos.getY(), other.getPos().getY());

        int maxW = (int) Math.max(w, other.getWidth());
        int maxH = (int) Math.max(h, other.getHeight());

        Vector2f pos = new Vector2f(minX, minY);
        return new AABB(pos, maxW, maxH);
	} 
	
	public String toString() {

		String x = Float.toString(pos.getX());
		String y = Float.toString(pos.getY());
		String w = Float.toString(this.w);
		String h = Float.toString(this.h);

		return "{" + x + ", " + y + " : " + w + ", " + h + "}";
	}

}
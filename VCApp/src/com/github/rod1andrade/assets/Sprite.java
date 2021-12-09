package com.github.rod1andrade.assets;

import java.awt.*;

/**
 *
 * @author Rodrigo Andrade
 */
public class Sprite {

    protected final int x;
    protected final int y;

    protected final int width;
    protected final int height;

    protected int scaleFactor = 1;
    protected boolean isScaled;

    protected final Image sprite;

    public Sprite(int x, int y, int width, int height, SpriteSheet spriteSheet) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        sprite = spriteSheet.crop(x, y, width, height);
    }

    public Image getSprite(int scale) {
        scaleFactor = scale;
        isScaled = true;
        return sprite.getScaledInstance(width * scale, height * scale, Image.SCALE_DEFAULT);
    }

    public Image getSprite() {
        return sprite;
    }

    public int getWidth() {
        return width * scaleFactor;
    }

    public int getHeight() {
        return height * scaleFactor;
    }

    public boolean isScaled() {
        return isScaled;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }
}

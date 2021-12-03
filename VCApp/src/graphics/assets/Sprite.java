package graphics.assets;

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

    protected int scaleFactor;
    protected boolean isScale;

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
        isScale = true;
        return sprite.getScaledInstance(width * scale, height * scale, Image.SCALE_DEFAULT);
    }

    public Image getSprite() {
        return sprite;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isScale() {
        return isScale;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }
}

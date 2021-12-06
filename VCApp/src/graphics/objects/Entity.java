package graphics.objects;

import graphics.assets.Sprite;

import java.awt.*;

/**
 *
 * @author Rodrigo Andrade
 */
public abstract class Entity {

    protected static final byte SCALE_FACTOR = 4;

    protected int posX;
    protected int posY;

    protected int width;
    protected int height;

    protected Sprite sprite;

    public Entity() {}

    public Entity(int posX, int posY, int width, int height, Sprite sprite) {
        this.posX = posX;
        this.posY = posY;
        this.width = width * SCALE_FACTOR;
        this.height = height * SCALE_FACTOR;
        this.sprite = sprite;
    }

    public abstract void update(float deltaTime);

    public void render(Graphics graphics) {
        graphics.drawImage(sprite.getSprite(SCALE_FACTOR), posX, posY, null);
    }
}

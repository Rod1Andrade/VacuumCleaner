package graphics.objects;

import graphics.assets.Sprite;

import java.awt.*;

/**
 *
 * @author Rodrigo Andrade
 */
public abstract class Entity {

    protected int posX;
    protected int posY;

    protected int width;
    protected int height;

    protected Sprite sprite;

    public Entity() {}

    public Entity(int posX, int posY, int width, int height, Sprite sprite) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public abstract void update(float deltaTime);

    public abstract void render(Graphics graphics);

}

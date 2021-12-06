package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;

import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public abstract class Entity {

    protected static final byte SCALE_FACTOR = 4;

    protected int posX;
    protected int posY;

    protected int width;
    protected int height;

    protected Sprite sprite;
    protected BoxCollision boxCollision;

    public Entity() {
    }

    public Entity(int posX, int posY, int width, int height, Sprite sprite, BoxCollision boxCollision) {
        this.posX = posX;
        this.posY = posY;
        this.width = width * SCALE_FACTOR;
        this.height = height * SCALE_FACTOR;
        this.sprite = sprite;
        this.boxCollision = boxCollision;
    }

    public abstract void update(float deltaTime);

    public void render(Graphics graphics) {
        graphics.drawImage(sprite.getSprite(SCALE_FACTOR), posX, posY, null);
    }

    /**
     * Verifica se a entidade e colisivel.
     *
     * @return TRUE caso seja e FALSE caso contrario.
     */
    public boolean isCollisible() {
        return boxCollision != null;
    }

    @Override
    public String toString() {
        return "0";
    }
}

package graphics.objects;

import graphics.assets.Sprite;
import graphics.enums.Mode;
import graphics.physics.BoxCollision;
import graphics.util.Info;

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

        setBoxCollision(boxCollision);
    }

    protected void setBoxCollision(BoxCollision boxCollision) {
        if (boxCollision != null) {
            boxCollision.setWidth(boxCollision.getWidth() * SCALE_FACTOR);
            boxCollision.setHeight(boxCollision.getHeight() * SCALE_FACTOR);
            this.boxCollision = boxCollision;
        }
    }

    public abstract void update(float deltaTime);

    /**
     * Renderizacao da entidade.
     *
     * @param graphics Graphics
     */
    public void render(Graphics graphics) {
        if (boxCollision != null && Info.mode == Mode.DEBUG) {
            String position = "(" + boxCollision.getX() + ", " + boxCollision.getY() + ")";

            graphics.setColor(Color.WHITE);
            graphics.drawString(position, boxCollision.getX(), boxCollision.getY());

            String size = "(" + boxCollision.getWidth() + ", " + boxCollision.getHeight() + ")";
            graphics.setColor(Color.WHITE);
            graphics.drawString(size, boxCollision.getX() + boxCollision.getWidth(), boxCollision.getY() + boxCollision.getHeight());

            graphics.setColor(Color.GREEN);
            graphics.drawRect(boxCollision.getX(), boxCollision.getY(), boxCollision.getWidth(), boxCollision.getHeight());
        }

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

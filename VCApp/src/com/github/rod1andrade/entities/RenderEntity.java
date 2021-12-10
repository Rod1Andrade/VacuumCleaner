package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Config;

import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public abstract class RenderEntity {

    protected static byte SCALE_FACTOR = 4;

    protected int posX;
    protected int posY;

    protected int width;
    protected int height;

    protected Sprite sprite;
    protected BoxCollision boxCollision;

    protected boolean isVisible;
    protected boolean isDebugMode;

    public RenderEntity() {
    }

    public RenderEntity(int posX, int posY, int width, int height, Sprite sprite, BoxCollision boxCollision, boolean isDebugMode) {
        this.posX = posX;
        this.posY = posY;
        this.width = width * SCALE_FACTOR;
        this.height = height * SCALE_FACTOR;
        this.sprite = sprite;
        this.isVisible = true;
        this.isDebugMode = isDebugMode;

        setBoxCollision(boxCollision);
    }

    protected void setBoxCollision(BoxCollision boxCollision) {
        if (boxCollision != null) {
            boxCollision.setWidth(boxCollision.getWidth() * SCALE_FACTOR);
            boxCollision.setHeight(boxCollision.getHeight() * SCALE_FACTOR);
            this.boxCollision = boxCollision;
        }
    }

    public void initResources() {
    }

    public abstract void update(float deltaTime);

    public void updateConfig(Config config) {
        this.isDebugMode = config.isDebugMode();
    }

    /**
     * Renderizacao da entidade.
     *
     * @param graphics Graphics
     */
    public void render(Graphics graphics) {
        if (isCollisible() && isDebugMode) {
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

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static byte getScaleFactor() {
        return SCALE_FACTOR;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public BoxCollision getBoxCollision() {
        return boxCollision;
    }

    @Override
    public String toString() {
        return "0";
    }
}

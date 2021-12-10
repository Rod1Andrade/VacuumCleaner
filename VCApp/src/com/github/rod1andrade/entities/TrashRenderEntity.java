package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public class TrashRenderEntity extends RenderEntity {

    private static final Sprite trashSprite = new Sprite(144, 112, 16, 16, Loader.spriteSheet001);

    public TrashRenderEntity(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, trashSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {

    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
}
